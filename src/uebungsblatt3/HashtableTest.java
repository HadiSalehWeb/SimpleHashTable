package uebungsblatt3;

import org.junit.Test;

import pr.MakeItSimple;

import static org.junit.Assert.*;

//import static pr.MakeItSimple.*;

import java.lang.invoke.MethodHandles;

public class HashtableTest {
    @Test
    public void put() {
        HashTable table = new HashTable();

        Element key1 = new StringElement("Key1"),
                key2 = new StringElement("Key2"),
                key3 = new StringElement("Key3"),
                key4 = new StringElement("Key4"),
                key5 = new StringElement("Key5");

        assertNull(table.put(key1, 1));
        assertNull(table.put(key2, 2));
        assertNull(table.put(key3, 3));
        assertEquals(3, table.put(key3, 3));

        assertEquals(3, table.size());

        assertTrue(table.containsKey(key1));
        assertTrue(table.containsKey(key2));
        assertTrue(table.containsKey(key3));
        assertFalse(table.containsKey(key4));
        assertFalse(table.containsKey(key5));

        assertTrue(table.contains(1));
        assertTrue(table.contains(2));
        assertTrue(table.contains(3));
        assertFalse(table.contains(4));
        assertFalse(table.contains(5));

        assertEquals(1, table.get(key1));
        assertEquals(2, table.get(key2));
        assertEquals(3, table.get(key3));

        assertEquals(null, table.get(key4));
        assertEquals(null, table.get(key5));
    }

    @Test
    public void isEmpty() {
        HashTable table = new HashTable();
        assertTrue(table.isEmpty());

        table.put(new StringElement("Test"), "Test");
        assertFalse(table.isEmpty());

        table.remove(new StringElement("Test"));
        assertTrue(table.isEmpty());
    }

    @Test
    public void size() {
        HashTable table = new HashTable();
        assertEquals(0, table.size());

        table.put(new StringElement("Test"), "Test");
        assertEquals(1, table.size());

        table.remove(new StringElement("Test"));
        assertEquals(0, table.size());

        table.put(new StringElement("Test1"), "Yeet");
        table.put(new StringElement("Test2"), "Yeetster");
        table.put(new StringElement("Test2"), "Yeetster2");
        assertEquals(2, table.size());

        table.remove(new StringElement("Test"));
        table.remove(new StringElement("Test1"));
        assertEquals(1, table.size());
    }

    @Test
    public void clear() {
        HashTable table = new HashTable();

        table.put(new StringElement("Test1"), "Yeet");
        table.put(new StringElement("Test2"), "Yeetster");
        table.put(new StringElement("Test3"), "Yeeeeeet");

        assertEquals(3, table.size());
        assertFalse(table.isEmpty());

        table.clear();

        assertEquals(0, table.size());
        assertTrue(table.isEmpty());
    }

    String s1 = "abcdefghijk";
    String s2 = "abcdefghijklmnop";
    String s3 = "abcdefghijklmnopqrst";
    String s4 = "abcdefghijklmnopqrstuvwxyz";
    String s5 = "Melissa Etheridge";
    String s6 = "abc";
    String s7 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaz";
    String s8 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaay";
    String s9 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaax";


    Element ms1 = new StringElement(s1);
    Element ms2 = new StringElement(s2);
    Element ms3 = new StringElement(s3);
    Element ms4 = new StringElement(s4);
    Element ms5 = new StringElement(s5);
    Element ms6 = new StringElement(s6);
    Element ms7 = new StringElement(s7);
    Element ms8 = new StringElement(s8);

    HashTable ht = new HashTable(5, new QuadraticProbing());

    @org.junit.Test
    public void testsOnEmptyHT() {
        assertEquals(0, ht.size());
        assertNull(ht.get(ms1));
        assertEquals(false, ht.contains(ms1));
        assertEquals(false, ht.containsKey(s1));
        assertEquals(true, ht.isEmpty());
        ht.clear();
    }

    @Test
    public void testsOnHT() {

        assertEquals(true, ht.isEmpty());

        assertNull(ht.put(ms1, s1));
        assertNull(ht.put(ms2, s2));
        assertNull(ht.put(ms3, s3));
        assertNull(ht.put(ms4, s4));

        assertEquals(s1, ht.put(ms1, s1 + s2));   // returns old value when same key is inserted
        assertEquals(4, ht.size());
        assertEquals(s2, ht.get(ms2));
        assertEquals(s4, ht.get(ms4));
        assertEquals(s1 + s2, ht.get(ms1));
        assertEquals(false, ht.contains(s1));
        assertEquals(true, ht.contains(s1 + s2));
        assertNull(ht.get(s6));
        assertEquals(false, ht.containsKey(ms5));
        assertEquals(true, ht.containsKey(ms2));
        assertEquals(false, ht.isEmpty());

        assertEquals(true, ht.remove(ms1));
        assertEquals(false, ht.remove(ms5));

        assertEquals(false, ht.contains(s1 + s2));
        assertEquals(true, ht.contains(s3));

        assertEquals(3, ht.size());
        assertEquals(true, ht.remove(ms2));
        assertEquals(true, ht.remove(ms3));
        assertEquals(true, ht.remove(ms4));

        assertEquals(0, ht.size());

        assertNull(ht.put(ms1, s1));
        assertNull(ht.put(ms2, s2));
        assertNull(ht.put(ms3, s3));
        assertNull(ht.put(ms4, s4));
        assertNull(ht.put(ms6, s4));

        assertEquals(5, ht.size());
        ht.clear();
        assertEquals(0, ht.size());

        assertNull(ht.put(ms1, s1));
        assertEquals(1, ht.size());
        assertNull(ht.put(ms2, s2));
        assertEquals(2, ht.size());

    }

    @Test
    public void testsOnSongFile() throws Exception {

        System.out.println(System.getProperty("java.class.path"));

        String filename = MethodHandles.lookup().lookupClass().getResource("songs.txt").getPath();

        HashTable songsTable = new HashTable(5, new LinearProbing());

        String[] songs = MakeItSimple.readStringArray(filename);

        for (String songString : songs) {
            String[] parts = songString.split(";");
            String[] artists = new String[parts.length - 2];
            for (int i = 2; i < parts.length; i++)
                artists[i - 2] = parts[i];

            Element artist = new StringElement(artists[0]);   // artist[0] = Künstler_1, artist[1] = Künstler_2
            Element singleSong = new StringElement(parts[0]); // parts[0] = Titel

            Song song = new SongImplementation(parts[0], artists, parts[1]); // parts[1] = Album

            songsTable.put(singleSong, song);

        }

        assertEquals(2810, songsTable.size());  // 2928 songs 118 duplicates

        String[] artists = {"Journey"};


        assertTrue(songsTable.containsKey(new StringElement("After The Fall")));
        assertNotNull(songsTable.get(new StringElement("After The Fall")));
        assertTrue(songsTable.contains(new SongImplementation("After The Fall", artists, "Frontiers")));

        artists[0] = "Pink Floyd";  // Title "Time" is a duplicate key

        assertTrue(songsTable.contains(new SongImplementation("Time", artists, "Ivor Wynne (Live In London June 28, 1975)")));
        assertFalse(songsTable.contains(new SongImplementation("Time", artists, "Pulse")));
        assertTrue(songsTable.containsKey(new StringElement("Time")));
        assertNotNull(songsTable.get(new StringElement("Time")));

    }

    @Test
    public void testsOnLongStrings() {

        HashTable ht = new HashTable(1, new LinearProbing(2, -1));

        assertTrue(ht.isEmpty());

        assertNull(ht.put(ms7, s7));
        assertEquals(1, ht.size());

        assertNull(ht.put(ms8, s8));
        assertEquals(2, ht.size());

        assertTrue(ht.containsKey(ms7));
        assertTrue(ht.containsKey(ms8));

        assertTrue(ht.contains(s7));
        assertFalse(ht.contains(s9));


    }

}