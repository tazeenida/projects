from django.db import models

class mastodonhubDashboard(models.Model):
    Title= models.CharField(max_length=120)
    Description= models.TextField()
    ImageUrl=models.URLField()

    def _str_(self):
        return self.Title

class mastodonhubEvents(models.Model):
    Title= models.CharField(max_length=120)
    Description= models.TextField()
    Location= models.CharField(max_length=120)
    Date= models.DateField()
    StartTime=models.TimeField()
    EndTime=models.TimeField()
    ImageUrl=models.URLField()
    Category=models.CharField(max_length=50)

    def _str_(self):
        return self.Title

class mastodonhubClubs(models.Model):
    Title= models.CharField(max_length=120)
    PresidentName= models.TextField()
    TreasurerName= models.TextField()
    AdvisorName= models.TextField()
    Email= models.TextField()
    ImageUrl=models.URLField()
    Category=models.CharField(max_length=50)
    def _str_(self):
        return self.Title