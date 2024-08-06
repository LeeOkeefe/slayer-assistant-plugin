package com.slayerassistant.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.slayerassistant.services.TaskService;
import com.slayerassistant.services.TaskServiceImpl;

public class TaskServiceModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(TaskService.class).to(TaskServiceImpl.class);
    }

    @Provides
    @Named("dataPath")
    String provideJsonDataPath()
    {
        return "/data/tasks.json";
    }

    @Provides
    @Named("baseWikiUrl")
    String provideBaseWikiUrl()
    {
        return "https://oldschool.runescape.wiki/w/";
    }

    @Provides
    @Named("baseImagesPath")
    String provideBaseImagesPath()
    {
        return "/images/monsters/";
    }
}
