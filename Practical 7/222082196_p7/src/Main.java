//Overall Main class: 30 marks ***********************************************
//Correctness (10 marks) ********************************
import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Main {
	/**
     * Extracts 8x8 grayscale patches from an image.
     * @param image Input BufferedImage.
     * @return PositionList of 8x8 patches.
     */
    public static PositionList<Patch> extractPatches(BufferedImage image) {
        PositionList<Patch> patches = new PositionList<>();
        for (int y = 0; y <= image.getHeight() - 8; y += 8) {
            for (int x = 0; x <= image.getWidth() - 8; x += 8) {
                double[][] patchData = new double[8][8];
                for (int dy = 0; dy < 8; dy++) {
                    for (int dx = 0; dx < 8; dx++) {
                        int rgb = image.getRGB(x + dx, y + dy);
                        int gray = ((rgb >> 16) & 0xff + (rgb >> 8) & 0xff + rgb & 0xff) / 3;
                        patchData[dy][dx] = gray;
                    }
                }
                patches.addLast(new Patch(patchData, x, y));
            }
        }
        return patches;
    }

    /**
     * Reconstructs an image from a list of patches.
     * @param patches PositionList of selected patches.
     * @param width Width of the final image.
     * @param height Height of the final image.
     * @return Reconstructed BufferedImage.
     * 10 marks ***********************************************
     */
    public static BufferedImage renderScene(PositionList<Patch> patches, int width, int height) {
        //TODO: Complete
    	BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
    	
    	for(Position<Patch> position = patches.first() ; position != null ; position = patches.next(position)  ) {
    		
    		Patch p = position.element();
    		if(p == null ) {
    			continue;
    		}
    		double[][] data = p.getData();
    		
    		for(int y = 0; y < 8 ; y++) {
    			for(int x = 0; x < 8 ; x++) {
    				
    				int setX = p.getX() + x;
    				int setY = p.getY() + y;
    				
    				if(setX < width && setY < height) {
    					
    					int gray = (int) Math.round(data[y][x]);
    					gray = Math.max(0, Math.min(255, gray));
    					
    					int rgb = (gray << 16) | (gray << 8) | gray;
    					image.setRGB(setX, setY, rgb);		
    					
    				}
    				
    			}
    		}
    		
    	}
		return image;
    		
    }


	public static void main(String[] args) throws IOException {
		// Load multiple images from the scene
        File[] imageFiles = new File("scenes").listFiles((dir, name) -> name.endsWith(".jpg"));
        PositionList<Patch> allPatches = new PositionList<>();

        for (File file : imageFiles) {
            BufferedImage img = ImageIO.read(file);
            PositionList<Patch> patches = extractPatches(img);
            for (Position<Patch> pos = patches.first(); pos != null; pos = patches.next(pos)) {
                allPatches.addLast(pos.element());
            }
        }
         
        // Heap insertion  - Use the custom Heap class with key = Hamming distance and value = Patch
        //5 marks ***********************************************
        //TODO: Complete
        Heap<Integer,Patch> heap = new Heap<>();
        for(Position<Patch> position = allPatches.first() ; position != null ; position = allPatches.next(position)) {
        	Patch p = position.element();
        	if(p != null) {
        		heap.insert(p.compareTo(p), p);
        	}
        }

        // Select top patches
        //5 marks ***********************************************
        PositionList<Patch> bestPatches = new PositionList<>();
        int patchCount = 100;
        //TODO: Complete
        while(!heap.isEmpty() && patchCount > 0) {
        	Entry<Integer, Patch> entry = heap.removeMin();
        	bestPatches.addLast(entry.getValue());
        	patchCount--;
        }

        // Reconstruct and save image
        BufferedImage result = renderScene(bestPatches, 800, 800); // assume 800x800 output
        ImageIO.write(result, "png", new File("completed_scene.png"));
    }
}