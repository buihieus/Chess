package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public abstract class Tile {
    protected final int tileCoordinate;
    private static final Map<Integer, EmptyTile> Empty_Tiles = createAllPossibleEmptyTile();

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTile() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        for (int i=0; i<BoardUtils.NUM_TILES; i++){
            emptyTileMap.put(i, new EmptyTile(i));
        }

        return ImmutableMap.copyOf(emptyTileMap);
    }

    public int getTileCoordinate() {
        return this.tileCoordinate;
    }

    private Tile(int tileCoordinate){
        this.tileCoordinate = tileCoordinate;
    }

    public static Tile createTile(final int tileCoordinate, final Piece piece){
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : Empty_Tiles.get(tileCoordinate);
    }

    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();
    public static final class EmptyTile extends Tile{
        EmptyTile(final int coordinate){
            super(coordinate);
        }
        @Override
        public boolean isTileOccupied() {
            return false;
        }
        @Override
        public Piece getPiece(){
            return null;
        }
        @Override
        public String toString(){
            return "-";
        }
    }
    public static final class OccupiedTile extends Tile{
        private final Piece pieceOnTile;
        OccupiedTile(final int tileCoordinate, final Piece pieceOnTile){
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }
        @Override
        public boolean isTileOccupied(){
            return true;
        }
        @Override
        public Piece getPiece(){
            return this.pieceOnTile;
        }
        @Override
        public String toString(){
            return getPiece().getPieceAlliance().isBlack() ? getPiece().toString().toLowerCase() : getPiece().toString();
        }
    }
}
