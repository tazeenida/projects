# Generated by Django 5.0.4 on 2024-11-15 23:21

import uuid
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('myapp', '0005_delete_experience'),
    ]

    operations = [
        migrations.CreateModel(
            name='Experience',
            fields=[
                ('experience_id', models.UUIDField(default=uuid.uuid4, editable=False, primary_key=True, serialize=False, unique=True)),
                ('company', models.CharField(blank=True, max_length=255, null=True)),
                ('role', models.CharField(blank=True, max_length=255, null=True)),
                ('start_month', models.CharField(blank=True, max_length=255, null=True)),
                ('start_year', models.PositiveSmallIntegerField(blank=True, null=True)),
                ('end_month', models.CharField(blank=True, max_length=255, null=True)),
                ('end_year', models.PositiveSmallIntegerField(blank=True, null=True)),
                ('job_description', models.TextField(blank=True, null=True)),
            ],
            options={
                'ordering': ['start_year', 'start_month'],
            },
        ),
    ]