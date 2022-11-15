package ru.croc.task7.src;

    public class Chess{
        protected int x,y;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public static Chess CreateChess(String pos) throws Exceptions.IllegalPositionException {
            if (pos.length() < 2) {
                throw new Exceptions.IllegalPositionException("Wrong position:" + pos);
            }
            int x = pos.charAt(0) - 'a';
            int y = pos.charAt(1) - '0' - 1;
            if (x < 0 || y < 0 || x > 7 || y > 7) {
                Exceptions.IllegalPositionException e = new Exceptions.IllegalPositionException("Wrong position:" + pos);
                System.out.println(e.getMessage());
                return null;

            }
            return new Chess(x,y);
        }

        private Chess(int x,int y)
        {
            this.x = x;
            this.y=y;
        }
        @Override
        public String toString()
        {
            char x = (char) ('a'+this.x);
            String positions= ""+x+(1+this.y);
            return positions;
        }
    }
