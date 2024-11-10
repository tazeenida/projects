from django.shortcuts import render
from rest_framework import viewsets
from .serializers import EducationSerializer, ProjectSerializer, SkillSerializer, ContactSerializer
from .models import Education, Project, Skill, Contact

class EducationView(viewsets.ModelViewSet):
	serializer_class=EducationSerializer
	queryset=Education.objects.all()
	
class ProjectView(viewsets.ModelViewSet):
	serializer_class=ProjectSerializer
	queryset=Project.objects.all()
	
class SkillView(viewsets.ModelViewSet):
	serializer_class=SkillSerializer
	queryset=Skill.objects.all()
	
class ContactView(viewsets.ModelViewSet):
	serializer_class=ContactSerializer
	queryset=Contact.objects.all()