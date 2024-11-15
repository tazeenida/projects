from rest_framework import serializers
from .models import Education, Project, Skill, Contact
from uuid import uuid4

class EducationSerializer(serializers.ModelSerializer):
	class Meta:
		model=Education
		fields= '__all__'
		
class ProjectSerializer(serializers.ModelSerializer):
	class Meta:
		model=Project
		fields= '__all__'
		
class SkillSerializer(serializers.ModelSerializer):
	class Meta:
		model=Skill
		fields='__all__'

class ContactSerializer(serializers.ModelSerializer):
	class Meta:
		model=Contact
		fields='__all__'