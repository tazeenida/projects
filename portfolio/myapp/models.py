from django.db import models
from uuid import uuid4
from django.core.exceptions import ValidationError
from django.utils.translation import gettext_lazy as _


class Experience(models.Model):
    experience_id = models.UUIDField(default=uuid4, editable=False, unique=True, primary_key=True)
    company = models.CharField(max_length=255, blank=True, null=True)
    role = models.CharField(max_length=255, blank=True, null=True)
    start_month = models.CharField(max_length=255, blank=True, null=True)
    start_year = models.PositiveSmallIntegerField(blank=True, null=True)
    end_month = models.CharField(max_length=255, blank=True, null=True)
    end_year = models.PositiveSmallIntegerField(blank=True, null=True)
    job_description = models.TextField(blank=True, null=True)
    
    class Meta:
        ordering = ['start_year', 'start_month']
    
    def __str__(self):
        return f"{self.role} at {self.company}" or 'No Experience'
    
    def clean(self):
        super().clean()
        if self.start_year and self.end_year:
            if self.end_year < self.start_year:
                raise ValidationError(_('End year cannot be earlier than the start year'))
                
LEVEL_CHOICES=[
	('bachelor', 'Bachelor'),
	('master', 'Master'),
	('postgraduate_diploma', 'Postgraduate Diploma')
]
class Education(models.Model):
	education_id=models.UUIDField(default=uuid4, editable=False, unique=True, primary_key=True)
	degree=models.CharField(max_length=255, blank=True, null=True)
	university=models.CharField(max_length=255, blank=True, null=True)
	level=models.CharField(max_length=255, choices=LEVEL_CHOICES, blank=True, null=True)
	start_month=models.CharField(max_length=255, blank=True, null=True)
	start_year=models.PositiveSmallIntegerField(blank=True, null=True)
	end_month=models.CharField(max_length=255, blank=True, null=True)
	end_year=models.PositiveSmallIntegerField(blank=True, null=True)
	
	class Meta:
		ordering=  ['start_year', 'start_month']
	
	def __str__(self):
		return self.degree or 'No Degree'
	
	def clean(self):
		super().clean()
		if self.start_year and self.end_year:
		  if self.end_year < self.start_year:
		      raise ValidationError(_('End year cannot be earlier than the start year'))
		
class Project(models.Model):
	project_id = models.UUIDField(default=uuid4, editable=False, unique=True, primary_key=True)
	title= models.CharField(max_length=255, unique=True)
	date=models.DateField(auto_now_add=True)
	role=models.CharField(max_length=255, blank=True, null=True)
	description=models.TextField(blank=True, null=True)
	key_technologies=models.TextField(blank=True, null=True)
	outcome=models.TextField(blank=True, null=True)
	link_to_github=models.URLField(blank=True, null=True)
	app_url=models.URLField(blank=True, null=True)
	skills = models.ManyToManyField('Skill', related_name='projects', blank=True)	
	
	def __str__(self):
		return self.title
	
class Skill(models.Model):
	skill_id=models.UUIDField(default=uuid4, editable=False, unique=True, primary_key=True)
	skill=models.CharField(max_length=255, unique=True)	
	def __str__(self):
		return self.skill
	
class Contact(models.Model):
	contact_id=models.UUIDField(default=uuid4, editable=False, unique=True, primary_key=True)
	email=models.EmailField(blank=True, null=True)
	github=models.URLField(blank=True, null=True)
	linkedin=models.URLField(blank=True, null=True)
	def __str__(self):
		return self.email or 'No Email'
	
	

	