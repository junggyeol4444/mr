package com.mr.jobclass;

import com.mr.jobclass.commands.JobCommand;
import com.mr.jobclass.listeners.JobListener;
import com.mr.jobclass.managers.JobManager;
import org.bukkit.plugin.java.JavaPlugin;

public class JobClassPlugin extends JavaPlugin {

    private static JobClassPlugin instance;
    private JobManager jobManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        jobManager = new JobManager(this);
        getCommand("job").setExecutor(new JobCommand(this));
        getServer().getPluginManager().registerEvents(new JobListener(this), this);
        getLogger().info("JobClassSystem이 활성화되었습니다.");
    }

    @Override
    public void onDisable() {
        getLogger().info("JobClassSystem이 비활성화되었습니다.");
    }

    public static JobClassPlugin getInstance() {
        return instance;
    }

    public JobManager getJobManager() {
        return jobManager;
    }
}
