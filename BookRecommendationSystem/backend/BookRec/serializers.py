from rest_framework import serializers
from BookRec.models import Books, Ratings
from uuid import uuid4

class RatingsSerializer(serializers.ModelSerializer):
    class Meta:
        model = Ratings
        fields = ['rating_id', 'book', 'average_rating', 'ratings_count']

class BooksSerializer(serializers.ModelSerializer):
    ratings = RatingsSerializer(many=True, read_only=True)

    class Meta:
        model = Books
        fields = [
            'book_id', 
            'title', 
            'year', 
            'pages', 
            'description', 
            'genres', 
            'authors', 
            'ratings' 
        ]
        read_only_fields = ['book_id']
