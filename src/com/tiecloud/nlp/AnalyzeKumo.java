package com.tiecloud.nlp;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.util.List;
import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.PixelBoundryBackground;
import com.kennycason.kumo.font.scale.LinearFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.nlp.tokenizers.ChineseWordTokenizer;
import com.kennycason.kumo.palette.ColorPalette;
import com.tiecloud.crawler.Crawler;

public class AnalyzeKumo {
	
	public static final String FILE_FOR_NLP = 
			System.getProperties().getProperty("user.dir") 
			+ java.io.File.pathSeparator
			+ Crawler.FETCHED_DATA_LOC
			+ java.io.File.pathSeparator
			+ "readyForNLP.txt";
	
	public static final String BACKGROUND = 
			System.getProperties().getProperty("user.dir") 
			+ java.io.File.pathSeparator
			+ "lib" + java.io.File.pathSeparator
			+ "background" + java.io.File.pathSeparator + "earth.png";
	
	public static final String OUTPUT_IMG_PATH = 
			System.getProperties().getProperty("user.dir") 
			+ java.io.File.pathSeparator
			+ Crawler.FETCHED_DATA_LOC + java.io.File.pathSeparator
			+ "output.png";
	
	public void getColud(String fileForNLP) throws IOException{
		
		
		final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
		frequencyAnalyzer.setWordFrequenciesToReturn(600);
		frequencyAnalyzer.setMinWordLength(2);
		frequencyAnalyzer.setWordTokenizer(new ChineseWordTokenizer());

		final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(fileForNLP);
		final Dimension dimension = new Dimension(600, 600);
		final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
		wordCloud.setPadding(2);
		
		wordCloud.setBackground(new PixelBoundryBackground(BACKGROUND));
		wordCloud.setColorPalette(new ColorPalette(new Color(0x4055F1), new Color(0x408DF1), new Color(0x40AAF1), new Color(0x40C5F1), new Color(0x40D3F1), new Color(0xFFFFFF)));
		wordCloud.setFontScalar(new LinearFontScalar(10, 40));
		wordCloud.build(wordFrequencies);
		wordCloud.writeToFile(OUTPUT_IMG_PATH);
		/*
		wordCloud.setBackground(new CircleBackground(300));
		wordCloud.setColorPalette(new ColorPalette(new Color(0xD5CFFA), new Color(0xBBB1FA), new Color(0x9A8CF5), new Color(0x806EF5)));
		wordCloud.setFontScalar(new SqrtFontScalar(12, 45));
		wordCloud.build(wordFrequencies);
		wordCloud.writeToFile("kumo-core/output/chinese_language_circle.png");
		*/
	}
}
