from django.contrib import admin
from django.urls import path, include
from rest_framework import routers
from myapp import views as portfolio_views
from rest_framework_simplejwt import views as jwt_views

router_portfolio= routers.DefaultRouter()
router_portfolio.register(r'education', portfolio_views.EducationView, basename='education')
router_portfolio.register(r'project', portfolio_views.ProjectView, basename='project')
router_portfolio.register(r'skill', portfolio_views.SkillView, basename='skill')
router_portfolio.register(r'contact', portfolio_views.ContactView, basename='contact')

urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/portfolio/', include(router_portfolio.urls)),
    path('token/', jwt_views.TokenObtainPairView.as_view(), name ='token_obtain_pair'),
    path('token/refresh/',jwt_views.TokenRefreshView.as_view(), name ='token_refresh'),
]
