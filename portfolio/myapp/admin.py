from django.contrib import admin
from .models import Education, Project, Skill, Contact


class BaseAdmin(admin.ModelAdmin):
	def has_add_permission(self, request,obj=None):
		return request.user.is_superuser
	
	def has_change_permission(self, request,obj=None):
		return request.user.is_superuser
	
	def has_delete_permission(self, request,obj=None):
		return request.user.is_superuser
		
@admin.register(Education)
class EducationAdmin(BaseAdmin):
	list_display=('education_id', 'degree', 'university', 'level', 'start_month','start_year', 'end_month', 'end_year')
	search_fields=('degree','university', 'level', 'start_month','start_year', 'end_month', 'end_year')
	list_filter=( 'level', 'start_month','start_year', 'end_month', 'end_year')
	read_only_fields=('education_id',)
	
	

@admin.register(Project)
class ProjectAdmin(BaseAdmin):
   list_display=('project_id', 'title', 'date', 'role', 'description', 'key_technologies', 'outcome', 'link_to_github', 'app_url')
   search_fields= ('title', 'role', 'key_technologies')
   list_filter= ('date', 'role', 'key_technologies')
   read_only_fields=('project_id',)

@admin.register(Skill)   
class SkillAdmin(BaseAdmin):
   list_display=('skill_id', 'skill')
   search_fields= ('skill',)
   list_filter= ('skill',)
   read_only_fields=('skill_id',)
   
@admin.register(Contact)
class ContactAdmin(BaseAdmin):
   list_display=('email', 'github', 'linkedin')
   search_fields= ('email', 'github', 'linkedin')
   list_filter= ('email', 'github', 'linkedin')
   read_only_fields= ('contact_id',)
