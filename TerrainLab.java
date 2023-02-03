package com.dh.etl.lmterrain;

import com.dh.etl.lmterrain.dao.terrainOptions;

/**
 * terrain file parse
 * 
 * @author admin
 *
 */
public class TerrainLab {
	public static void main(String[] args) {

		String gimPath = "E:\\models\\dem\\china\\14\\";

		// parse and get terrain mesh
		// TerrainParser parser = new TerrainParser(gimPath);
		// parser.parse();

		// convert tif to cesium terrain
		TiffParser tifParser = new TiffParser();
		terrainOptions options = new terrainOptions();
		//set terrain max zoom
		options.startZoom = 13;
		options.tifPath = "E:\\models\\dem\\tifs\\";
		options.outPath = "E:\\models\\dem\\test\\";
		tifParser.startup(options);
		tifParser.destroy();
		System.out.println("success! ");
	}
}
