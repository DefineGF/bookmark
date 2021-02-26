##### 引用

```java
implementation "androidx.room:room-runtime:2.2.5"
annotationProcessor 'android.arch.persistence.room:compiler:1.1.1'
testImplementation "androidx.room:room-testing:2.2.5" // Test helpers
```

##### 实体类

```java
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Word {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "english_word")
    private String word;

    @ColumnInfo(name = "chinese_word")
    private String chinese;

    public Word(String word, String chinese) {
        this.word = word;
        this.chinese = chinese;
    }

    public int getId() {	return id;}

    public void setId(int id) {	this.id = id;}

    public String getWord() {	return word;}

    public void setWord(String word) {	this.word = word;}

    public String getChinese() {	return chinese;}

    public void setChinese(String chinese) {	this.chinese = chinese;}
}
```

##### 数据库操作类

```java
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface WordDao {
    @Insert
    void insertWords(Word... words);

    @Update
    void updateWords(Word... words);

    @Delete
    void deleteWords(Word... words);

    @Query("DELETE FROM WORD")
    void deleteAllWords();

    @Query("SELECT * FROM WORD ORDER BY ID DESC")
    List<Word> getALLWords();
}
```

##### 数据源

```java
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {
    private static WordDatabase INSTANCE;
    public static WordDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, WordDatabase.class, "word_database").build();
        }
        return INSTANCE;
    }
    
    public abstract WordDao getWordDao();
}
```



##### 具体例子

```java
WordDatabase database = WordDatabase.getInstance(this);
wordDao = database.getWordDao();

LiveData<List<Word>> liveListWords = wordDao.getAllLiveWords();
liveListWords.observe(this, new Observer<List<Word>>() {
    @Override
    public void onChanged(List<Word> words) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            Word w = words.get(i);
            String s = "id = " + w.getId() + " ;word = " + w.getWord() + " ;chinese = " + w.getChinese();
            sb.append(s).append("\n");
        }
        tvDisplay.setText(sb.toString());
    }
});
```















