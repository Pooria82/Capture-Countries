import java.util.ArrayList;

public class Unit extends Thread {
    public Coord now, end;
    public Player player;
    public int power = 1;

    Unit(Player p, Coord n, Coord e) {
        this.now = n;
        this.end = e;
        this.player = p;
    }

    public Coord getNow() {
        return now;
    }

    public void setNow(Coord now) {
        this.now = now;
    }

    public Coord getEnd() {
        return end;
    }

    public void setEnd(Coord end) {
        this.end = end;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public void run() {
        //super.run();
        try {
            boolean ended = false;
            //double dis= Math.sqrt(Math.pow(Math.abs(end.x - now.x), 2) + Math.pow((Math.abs(end.y - now.y)), 2));
            while (!ended) {
                int dify = Math.abs(now.y - end.y);
                int difx = Math.abs(now.x - end.x);
                if (difx > dify) {
                    double sh = Math.min((double) difx / dify, 3);
                    if (now.x > end.x) {
                        synchronized (now) {
                            now.x -= (int) sh;
                        }
                    } else if (now.x < end.x) {
                        synchronized (now) {
                            now.x += (int) sh;
                        }
                    }
                    if (now.y > end.y) {
                        synchronized (now) {
                            now.y--;
                        }
                    } else if (now.y < end.y) {
                        synchronized (now) {
                            now.y++;
                        }
                    }
                } else if (dify > difx) {
                    double sh = Math.min((double) dify / difx, 3);
                    if (now.x > end.x) {
                        synchronized (now) {
                            now.x--;
                        }
                    } else if (now.x < end.x) {
                        synchronized (now) {
                            now.x++;
                        }
                    }
                    if (now.y > end.y) {
                        synchronized (now) {
                            now.y -= (int) sh;
                        }
                    } else if (now.y < end.y) {
                        synchronized (now) {
                            now.y += (int) sh;
                        }
                    }
                } else {
                    double sh = 1;
                    if (now.x > end.x) {
                        synchronized (now) {
                            now.x--;
                        }
                    } else if (now.x < end.x) {
                        synchronized (now) {
                            now.x++;
                        }
                    }
                    if (now.y > end.y) {
                        synchronized (now) {
                            now.y--;
                        }
                    } else if (now.y < end.y) {
                        synchronized (now) {
                            now.y++;
                        }
                    }
                }

                if (Math.sqrt(Math.pow(Math.abs(end.x - now.x), 2) + Math.pow((Math.abs(end.y - now.y)), 2)) < 20) {
                    synchronized (Game.countries) {
                        for (Country c : Game.countries) {
                            if (c.center.equals(end)) {
                                c.GetUnit(this);
                                ended = true;
                                this.interrupt();
                            }
                        }
                    }

                }
                sleep(20);

            }
        } catch (Exception ez) {

        }
    }

    @Override
    public boolean equals(Object obj) {
        try {
            return player.equals(((Unit) obj).player) && now.equals(((Unit) obj).now);
        } catch (Exception ex) {
            return false;
        }
    }
}
