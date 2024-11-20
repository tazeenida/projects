from rest_framework import serializers
from .models import mastodonhubEvents, mastodonhubClubs, mastodonhubDashboard

class DashboardSerializer(serializers.ModelSerializer):
    class Meta:
        model =  mastodonhubDashboard
        fields = ('Title','Description', 'ImageUrl')

class EventsSerializer(serializers.ModelSerializer):
    class Meta:
        model =  mastodonhubEvents
        fields = ('Title','Description', 'Location', 'Date', 'StartTime', 'EndTime', 'ImageUrl', 'Category' )

class ClubsSerializer(serializers.ModelSerializer):
    class Meta:
        model =  mastodonhubClubs
        fields = ('Title','PresidentName','TreasurerName', 'AdvisorName', 'Email', 'ImageUrl', 'Category')