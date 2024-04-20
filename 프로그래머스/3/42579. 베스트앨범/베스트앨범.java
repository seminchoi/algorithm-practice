import java.util.*;

class Solution {
    static class Music {
        static Map<String, Integer> playTable = new HashMap<>();
        
        int id;
        String genre;
        int play;
        
        public Music(int id, String genre, int play) {
            this.id = id;
            this.genre = genre;
            this.play = play;
        }
        
        public void updateTable() {
            int curPlay = playTable.getOrDefault(genre, 0);
            playTable.put(genre, curPlay + this.play);
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        List<Music> musics = createMusics(genres, plays);

        return createAnswer(musics);
    }
    
    private List<Music> createMusics(String[] genres, int[] plays) {
        int length = genres.length;
        
        List<Music> musics = new ArrayList<>();
        for(int i = 0; i < length; i++) {
            Music music = new Music(i, genres[i], plays[i]);
            musics.add(music);
            music.updateTable();
        }
        
        Collections.sort(musics, (m1, m2) -> {
            if(m1.genre.equals(m2.genre)) {
                if(m1.play == m2.play) {
                    return m1.id - m2.id;
                }
                return m2.play - m1.play;
            }
            return Music.playTable.get(m2.genre) - Music.playTable.get(m1.genre);
        });
            
        return musics;
    }
        
    private int[] createAnswer(List<Music> musics) {
        List<Integer> ids = new ArrayList<>();
        String curGenre = "";
        int count = 0;
        for(Music music : musics) {
            if(!curGenre.equals(music.genre)) {
                count = 0;
                curGenre = music.genre;
            }
            
            if(count >= 2) {
                continue;
            }
            
            ids.add(music.id);
            count++;
        }
        
        return ids.stream().mapToInt(Integer::intValue).toArray();
    }
}