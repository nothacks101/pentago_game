package com.pentago.ComputerScripts;

public class FindQuadrant {
    public int find(int index)
    {
        int row = index / 6;
        int col = index % 6;

        if  (row < 3 && col >= 3)
        {
            return 1;
        } else if (row < 3 && col < 3)
        {
            return 4;
        } else if (row >= 3 && col < 3)
        {
            return 3;
        } else
        {
            return 2;
        }
    }
}
