from django.urls import path
from . import views
from .views import LogoutView, SignUpView, get_user_details 


urlpatterns = [
    path('logout/', views.LogoutView.as_view(), name ='logout'),
    path('signUp/', views.SignUpView.as_view(), name ='signUp'),
    path('profile/', get_user_details, name='user_profile'),
]