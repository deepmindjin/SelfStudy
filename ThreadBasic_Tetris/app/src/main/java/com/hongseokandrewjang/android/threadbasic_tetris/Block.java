package com.hongseokandrewjang.android.threadbasic_tetris;

import android.os.Handler;

/**
 * Created by HongSeokAndrewJang on 2016-10-17.
 */

public class Block extends Thread {

    private static final int ABS_X = 5;
    private static final int ABS_Y = 0;

    int x = 0;
    int y = 0;
    boolean alive = false;
    Handler handler;

    // 현재 회전방향 처음엔 0으로 셋팅
    private int currentOrientation = 0;
    private int currentOrientationLimit = 4;
    private int[][][] currentBlockGroup;
    private int[][] currentBlock;

    public int[][] getCurrentBlock() {
        return currentBlock;
    }

    // Construct random block
    public Block(int type, Handler handler) { // 0 to 6
        // First position of new block
        x = ABS_X;
        y = ABS_Y;
        this.handler = handler;

        currentBlockGroup = blocks[type];
        currentOrientation = 0;
        currentBlock = blocks[type][currentOrientation];
    }

    // Change the orientation of block
    // Rotated block shape is already set in the array
    // dividing orientation by orientationLimit(4), we can set it's shape

    public void rotateBlock() {
        currentOrientation = currentOrientation + 1;
        currentOrientation = currentOrientation % currentOrientationLimit;
        currentBlock = currentBlockGroup[currentOrientation];
    }

    public void unRotateBlock() {
        if (currentOrientation!=0) {
            currentOrientation = currentOrientation - 1;
        }else{
            currentOrientation = 3;
        }
    }

    @Override
    public void run() {
        try {
            while (alive) {
                try {
                    handler.sendEmptyMessage(MainActivity.BLOCK_GOING_DOWN);
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            }
            interrupt();
        }catch (Exception e){}
    }

    int blocks[][][][] = {
            {
                    // Block I
                    {
                            {0, 1, 0, 0},
                            {0, 1, 0, 0},
                            {0, 1, 0, 0},
                            {0, 1, 0, 0}
                    },
                    {
                            {1, 1, 1, 1},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 1, 0, 0},
                            {0, 1, 0, 0},
                            {0, 1, 0, 0},
                            {0, 1, 0, 0}
                    },
                    {
                            {1, 1, 1, 1},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    }
            },
            // Block J
            {
                    {
                            {0, 0, 2, 0},
                            {0, 0, 2, 0},
                            {0, 2, 2, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 2, 0, 0},
                            {0, 2, 2, 2},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 2, 2, 0},
                            {0, 2, 0, 0},
                            {0, 2, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {2, 2, 2, 0},
                            {0, 0, 2, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    }
            },
            // Block L
            {
                    {
                            {0, 3, 0, 0},
                            {0, 3, 0, 0},
                            {0, 3, 3, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 3, 3, 3},
                            {0, 3, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 3, 3, 0},
                            {0, 0, 3, 0},
                            {0, 0, 3, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 0, 3, 0},
                            {3, 3, 3, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    }
            },
            // Block S
            {
                    {
                            {0, 4, 4, 0},
                            {4, 4, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 4, 0, 0},
                            {0, 4, 4, 0},
                            {0, 0, 4, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 4, 4, 0},
                            {4, 4, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 4, 0, 0},
                            {0, 4, 4, 0},
                            {0, 0, 4, 0},
                            {0, 0, 0, 0}
                    }
            },
            // Block Z
            {
                    {
                            {0, 5, 5, 0},
                            {0, 0, 5, 5},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 0, 5, 0},
                            {0, 5, 5, 0},
                            {0, 5, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 5, 5, 0},
                            {0, 0, 5, 5},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 0, 5, 0},
                            {0, 5, 5, 0},
                            {0, 5, 0, 0},
                            {0, 0, 0, 0}
                    }
            },
            // Block T
            {
                    {
                            {0, 6, 0, 0},
                            {0, 6, 6, 0},
                            {0, 6, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {6, 6, 6, 0},
                            {0, 6, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                    },
                    {
                            {0, 6, 0, 0},
                            {6, 6, 0, 0},
                            {0, 6, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 6, 0, 0},
                            {6, 6, 6, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    }
            },
            // Block O
            {
                    {
                            {0, 7, 7, 0},
                            {0, 7, 7, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 7, 7, 0},
                            {0, 7, 7, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 7, 7, 0},
                            {0, 7, 7, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 7, 7, 0},
                            {0, 7, 7, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    },
            },
    };
}
