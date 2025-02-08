from django.urls import path
from . import views

urlpatterns = [
    path('add/', views.BookRecView.as_view({'post': 'add'}), name='add'),
    path('delete/<str:book_id>/', views.BookRecView.as_view({'delete': 'delete'}), name='delete'),
    path('filter/', views.BookRecView.as_view({'get': 'filter'}), name='filter'),
    path('update/', views.BookRecView.as_view({'put': 'update'}), name='update'),
]
