from django.contrib import admin
from .models import mastodonhubDashboard, mastodonhubEvents, mastodonhubClubs

class DashboardAdmin(admin.ModelAdmin):
    list_display = ('Title','Description', 'ImageUrl')

class EventsAdmin(admin.ModelAdmin):
    list_display = ('Title','Description', 'Location', 'Date', 'StartTime', 'EndTime', 'ImageUrl', 'Category' )

class ClubsAdmin(admin.ModelAdmin):
    list_display= ('Title','PresidentName','TreasurerName', 'AdvisorName', 'Email', 'ImageUrl', 'Category')


admin.site.register(mastodonhubDashboard, DashboardAdmin)
admin.site.register(mastodonhubEvents, EventsAdmin)
admin.site.register(mastodonhubClubs, ClubsAdmin)