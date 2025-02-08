from django.db import models
from uuid import uuid4

class Books(models.Model):
    book_id = models.UUIDField(default=uuid4, editable=False, unique=True, primary_key=True)
    title = models.TextField(unique=True)
    year = models.IntegerField(default=0)
    pages = models.IntegerField(default=0)
    description = models.TextField(default='None')
    genres = models.TextField(default='None')
    authors = models.TextField(default='Jane Doe')

    def __str__(self):
        return self.title

class Ratings(models.Model):
    book = models.ForeignKey(Books, on_delete=models.CASCADE, related_name='ratings')
    rating_id = models.UUIDField(default=uuid4, editable=False, unique=True, primary_key=True)
    average_rating = models.FloatField(default=0)
    ratings_count = models.IntegerField(default=0)

    def __str__(self):
        return self.rating_id
