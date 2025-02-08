from django.db import connection
from rest_framework import viewsets
from rest_framework.decorators import action
from rest_framework.response import Response
from rest_framework import status
from .serializers import BooksSerializer, RatingsSerializer
from .models import Books, Ratings
from django.http import JsonResponse
from django.db import connection, transaction
from rest_framework.decorators import action
from uuid import uuid4
import json

class BookRecView(viewsets.ViewSet):
    def list(self, request):
        try:
            with connection.cursor() as cursor:
                cursor.execute(
                    "SELECT * FROM bookrec_books JOIN bookrec_ratings ON bookrec_ratings.book_id = bookrec_books.book_id"
                )
                rows = cursor.fetchall()

            result = [
                dict(zip([column[0] for column in cursor.description], row))
                for row in rows
            ]

            return JsonResponse(result, safe=False)

        except Exception as e:
            return Response({"error": str(e)}, status=status.HTTP_500_INTERNAL_SERVER_ERROR)

    @action(detail=False, methods=['post'])
    def add(self, request):
        book_id = uuid4().hex
        rating_id = uuid4().hex

        title = request.data.get('title')
        year = request.data.get('year', 0)
        pages = request.data.get('pages', 0)
        description = request.data.get('description', 'None')
        genres = request.data.get('genres', 'None')
        average_rating = request.data.get('average_rating', 0.0)
        ratings_count = request.data.get('ratings_count', 0)
        authors = request.data.get('authors', 'Jane Doe')

        try:
            with transaction.atomic():
                with connection.cursor() as cursor:
                    cursor.execute(
                        "INSERT INTO bookrec_books (book_id, title, year, pages, description, genres, authors) "
                        "VALUES (%s, %s, %s, %s, %s, %s, %s)",
                        [book_id, title, year, pages, description, genres, authors]
                    )

                    cursor.execute(
                        "INSERT INTO bookrec_ratings (rating_id, book_id, average_rating, ratings_count)"
                        "VALUES (%s, %s, %s, %s)",
                        [rating_id, book_id, average_rating, ratings_count]
                    )

            return JsonResponse({'message': 'Book and rating added successfully'}, status=status.HTTP_201_CREATED)

        except Exception as e:
            import logging
            logging.error("Error adding book:", exc_info=True)
            return JsonResponse({'error': str(e)}, status=status.HTTP_500_INTERNAL_SERVER_ERROR)

    @action(detail=False, methods=['get'])
    def filter(self, request):
        query = "SELECT bookrec_books.*, bookrec_ratings.average_rating, bookrec_ratings.ratings_count FROM bookrec_books JOIN bookrec_ratings ON bookrec_ratings.book_id = bookrec_books.book_id WHERE 1=1"
        params = []

        average_rating_min = request.query_params.get("average_rating_min")
        average_rating_max = request.query_params.get("average_rating_max")

        if average_rating_min:
            query += " AND bookrec_ratings.average_rating >= %s"
            params.append(average_rating_min)

        if average_rating_max:
            query += " AND bookrec_ratings.average_rating <= %s"
            params.append(average_rating_max)

        fields = {
            'book_id': 'book_id',
            'title': 'title',
            'year': 'year',
            'pages': 'pages',
            'description': 'description',
            'genres': 'genres',
            'ratings_count': 'ratings_count',
            'authors': 'authors',
        }

        for field, column in fields.items():
            value = request.query_params.get(field)
            if value:
                query += f" AND bookrec_books.{column} LIKE %s"
                params.append(f"%{value}%")

        try:
            with connection.cursor() as cursor:
                cursor.execute(query, params)
                rows = cursor.fetchall()

            result = [dict(zip([col[0] for col in cursor.description], row)) for row in rows]

            return JsonResponse(result, safe=False)

        except Exception as e:
            return JsonResponse({"error": str(e)}, status=status.HTTP_500_INTERNAL_SERVER_ERROR)

    def update(self, request, pk=None):
        data = json.loads(request.body)
        book_id = data.get("book_id")

        if not book_id:
            return JsonResponse(
                {"error": "Book ID is required for update."},
                status=status.HTTP_400_BAD_REQUEST,
            )

        books_update_fields = []
        books_update_values = []

        ratings_update_fields = []
        ratings_update_values = []

        # Check which fields need updating
        books_fields_to_check = {
            "title": "title",
            "authors": "authors",
            "year": "year",
            "pages": "pages",
            "description": "description",
            "genres": "genres",
        }

        ratings_fields_to_check = {
            "average_rating": "average_rating",
            "ratings_count": "ratings_count",
        }

        for key, column in books_fields_to_check.items():
            value = data.get(key)
            if value:
                books_update_fields.append(f"{column} = %s")
                books_update_values.append(value)

        for key, column in ratings_fields_to_check.items():
            value = data.get(key)
            if value:
                ratings_update_fields.append(f"{column} = %s")
                ratings_update_values.append(value)

        if not books_update_fields and not ratings_update_fields:
            return JsonResponse(
                {"message": "No valid fields provided for update."},
                status=status.HTTP_200_OK,
            )

        books_update_values.append(book_id)
        ratings_update_values.append(book_id)

        try:
            with transaction.atomic():
                with connection.cursor() as cursor:
                    if books_update_fields:
                        books_update_query = (
                            "UPDATE bookrec_books SET "
                            + ", ".join(books_update_fields)
                            + " WHERE book_id = %s"
                        )
                        cursor.execute(books_update_query, books_update_values)

                    if ratings_update_fields:
                        ratings_update_query = (
                            "UPDATE bookrec_ratings SET "
                            + ", ".join(ratings_update_fields)
                            + " WHERE book_id = %s"
                        )
                        cursor.execute(ratings_update_query, ratings_update_values)

            return JsonResponse(
                {"status": "success", "updated_fields": {"books": books_update_fields, "ratings": ratings_update_fields}},
                status=status.HTTP_200_OK,
            )

        except Exception as e:
            return JsonResponse(
                {"error": str(e)},
                status=status.HTTP_500_INTERNAL_SERVER_ERROR,
            )
    @action(detail=False, methods=['delete'])
    def delete(self, request):
        title = request.data.get('title')
        authors = request.data.get('authors')

        if not title or not authors:
            return Response(
                {"error": "Title and authors are required to delete a book."},
                status=status.HTTP_400_BAD_REQUEST,
            )

        try:
            with transaction.atomic():  # Ensure both operations are atomic
                with connection.cursor() as cursor:
                    # Delete associated ratings first
                    cursor.execute(
                        "DELETE FROM bookrec_ratings WHERE book_id IN (SELECT book_id FROM bookrec_books WHERE title = %s AND authors = %s)",
                        [title, authors],
                    )

                    # Now delete the book
                    cursor.execute(
                        "DELETE FROM bookrec_books WHERE title = %s AND authors = %s",
                        [title, authors],
                    )
            
            return JsonResponse({'message': 'Book and associated ratings deleted successfully'}, status=status.HTTP_200_OK)

        except Exception as e:
            return JsonResponse(
                {"error": str(e)},
                status=status.HTTP_500_INTERNAL_SERVER_ERROR,
            )