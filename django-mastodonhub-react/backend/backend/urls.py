from django.contrib import admin
from django.urls import path, include
from rest_framework import routers
from mastodonhub import views as mastodonhub_views
from rest_framework_simplejwt import views as jwt_views

# Define the router for mastodonhub endpoints with explicit basenames
router_mastodon_clubs_events = routers.DefaultRouter()
router_mastodon_clubs_events.register(r'dashboard', mastodonhub_views.DashboardView, basename='dashboard')
router_mastodon_clubs_events.register(r'clubs', mastodonhub_views.ClubsView, basename='clubs')
router_mastodon_clubs_events.register(r'events', mastodonhub_views.EventsView, basename='events')

# Define urlpatterns
urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/mastodonhub/', include(router_mastodon_clubs_events.urls)), 
    path('token/', jwt_views.TokenObtainPairView.as_view(), name ='token_obtain_pair'),
     path('token/refresh/',jwt_views.TokenRefreshView.as_view(), name ='token_refresh'),
     path('', include('authentification.urls')),
]
