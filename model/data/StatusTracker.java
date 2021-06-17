package model.data;

public class StatusTracker {
    private int status;
    private int DRM;

    public void modifyDRM(int add) {
        DRM += add;
    }

    public void resetDRM() {
        DRM = 0;
    }

    public int getDRM() {
        return DRM;
    }

    public StatusTracker() {
        status = 4;
        DRM = 0;
    }

    public void reduceStatus(int loss) {
        if(status > 0) {
            status -= loss;
            if(status < 0) status = 0;
        }
    }

    public void increaseStatus(int gain) {
        if(status < 4) {
            status += gain;
            if(status > 4) status = 4;
        }
    }

    public int getStatus() {
        return status;
    }
}
