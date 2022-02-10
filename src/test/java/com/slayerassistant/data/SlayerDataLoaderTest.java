package com.slayerassistant.data;

import com.slayerassistant.domain.Item;
import com.slayerassistant.domain.SlayerTask;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.FileNotFoundException;
import java.util.Collection;

import static java.util.Optional.empty;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;

public class SlayerDataLoaderTest
{
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void loadReturnsCollectionOfSlayerTasks() throws FileNotFoundException
    {
        SlayerDataLoader dataLoader = new SlayerDataLoader();

        Collection<SlayerTask> tasks = dataLoader.load();

        Assert.assertThat(tasks, not(empty()));
        Assert.assertThat(tasks, not(nullValue()));
    }

    @Test
    public void loadReturnsImmutableCollectionThatThrowsExceptionWhenModified() throws FileNotFoundException
    {
        exception.expect(UnsupportedOperationException.class);

        SlayerDataLoader dataLoader = new SlayerDataLoader();

        Collection<SlayerTask> tasks = dataLoader.load();

        String[] strings = new String[]{};

        tasks.add(new SlayerTask("monster", 1, strings, new Item[]{}, strings, strings, strings, strings));
    }
}
