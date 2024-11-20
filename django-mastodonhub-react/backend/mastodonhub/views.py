from django.shortcuts import render
from rest_framework import viewsets
from .serializers import DashboardSerializer, ClubsSerializer, EventsSerializer
from .models import mastodonhubDashboard, mastodonhubEvents, mastodonhubClubs

class DashboardView(viewsets.ModelViewSet):
    serializer_class = DashboardSerializer
    queryset = mastodonhubDashboard.objects.all()

class EventsView(viewsets.ModelViewSet):
    serializer_class = EventsSerializer
    queryset = mastodonhubEvents.objects.all()

class ClubsView(viewsets.ModelViewSet):
    serializer_class = ClubsSerializer
    queryset = mastodonhubClubs.objects.all()