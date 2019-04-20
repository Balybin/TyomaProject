package ru.android.tyomaproject;

public interface MainContract {
    interface View{
        void initialiseComponents();
    }
    interface Presenter{
        void getDataWithUserId();
    }
    interface Model{
        void loadDataWithUserId();

    }
}
