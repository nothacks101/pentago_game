package com.pentago.LineEvaluator;

public class LineValue {
    public int getValue(int bit_count){
        switch (bit_count) {
            case 5: return 100000; // ניצחון
            case 4: return 1000;   // כמעט ניצחון
            case 3: return 100;    // התחלה של רצף
            case 2: return 10;     
            default: return 0;     // 0 או 1 ביט → לא שווה משהו
        }
    }
}
