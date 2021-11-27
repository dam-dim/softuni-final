package bg.softuni.final_project.service;

import bg.softuni.final_project.model.view.StatsViewModel;

public interface StatsService {
    void onRequest();
    StatsViewModel getStats();
}
