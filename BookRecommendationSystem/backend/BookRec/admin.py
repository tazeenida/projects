from django.contrib import admin
from .models import Books, Ratings

class BooksAdmin(admin.ModelAdmin):
    list_display = ('book_id', 'title', 'year', 'pages', 'description', 'genres', 'authors')
    search_fields = ['title', 'authors'] 
    list_filter = ['genres', 'year'] 
    read_only_fields = ['book_id'] 
    

class RatingsAdmin(admin.ModelAdmin):
    list_display = ( 'rating_id', 'book', 'average_rating', 'ratings_count')
    ordering = ['-average_rating'] 
admin.site.register(Books, BooksAdmin)
admin.site.register(Ratings, RatingsAdmin)

