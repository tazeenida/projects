from django.contrib import admin
from django.urls import path, include
from rest_framework import routers
from BookRec import views

router = routers.DefaultRouter()
router.register(r'BookRec', views.BookRecView, 'BookRec')

urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/', include(router.urls)),
]
