public class MusicLibrary {
    public static void main(String[] args) {
        // repeat as long as there are more integers to read in
        while (!StdIn.isEmpty()) {
            // read in the pitch, where 0 = Concert A (A4)
            int pitch = StdIn.readInt();
            double hz = 440 * Math.pow(2, pitch / 12.0);
            // read in duration in seconds
            double duration = StdIn.readDouble();

            // StdAudio.play(harmonics(pitch, duration));
            // StdAudio.play(majorChord(pitch, duration));
            // StdAudio.play(minorChord(pitch, duration));
            // StdAudio.play(changeVolume(minorChord(pitch, duration),10));
           	// StdAudio.play(fadeIn(minorChord(pitch,duration),2));
           	// StdAudio.play(fadeOut(majorChord(pitch,duration),2));
           	
           	double[] a = {0,0,0,0,0,0,0,22,28,91,34,18,29,22,0,0,0,0,0,0};
           	double[] b = {0,0,0,0,0,0,0,22,28,91,34,18,29,22,0,0,0,0,0,0};
           	// ArrayTools.printArray(concatenateArray(a,b));
           	// ArrayTools.printArray(echo(sinstuff(duration,hz),10,1,2));
           	// StdAudio.play(echo(majorChord(pitch,duration),10,.5,5));
            
        }
    }
    
    public static double[] harmonics(int pitch, double duration) {
        double hz = 440.0 * Math.pow(2, pitch / 12.0);
        double[] hi = sinstuff(duration, 2*hz);
        double[] lo = sinstuff(duration, hz/2);
        double[] h  = sum(hi, lo, 0.5, 0.5);
        return h;
    }

    public static double[] majorChord(int pitch, double duration) {
        double[] a = sinstuff(duration, 440 * Math.pow(2, pitch / 12.0));
        double[] b = sinstuff(duration, 440 * Math.pow(2, (pitch + 4) / 12.0));
        double[] c = sinstuff(duration, 440 * Math.pow(2, (pitch + 7) / 12.0));

        double[] e = sum(a, b, 0.5, 0.5);
        double[] f = sum(e, c, 0.5, 0.5);
        return f;
    }

    public static double[] minorChord(int pitch, double duration) {
        double[] a = sinstuff(duration, 440 * Math.pow(2, pitch / 12.0));
        double[] b = sinstuff(duration, 440 * Math.pow(2, (pitch + 3) / 12.0));
        double[] c = sinstuff(duration, 440 * Math.pow(2, (pitch + 7) / 12.0));

        double[] e = sum(a, b, 0.5, 0.5);
        double[] f = sum(e, c, 0.5, 0.5);
        return f;
    }

    public static double[] changeVolume(double[] a, double volume) {
        return ArrayTools.scale(a, volume);
    }

    public static double[] sum(double[] a, double[] b, double c, double d) {
    	return ArrayTools.add(a, b, c, d);
    }

    public static double[] clip(double[] a, double b) {
    	for (int i = 0; i < a.length; i++) {
			if (a[i] >= b) {
				a[i] = b;
			}
		}
		return a;
    }

    public static double[] trim(double[] a) {
    	int index1 = 0;
    	int length = a.length;
    	for (int i = 0; i < length; i++) {
			if (a[i] ==0) {
				index1 = i;
			} else {
				length = i;
			}
		}

		length = 0;
		int index2 = 0;
		for (int y = a.length-1; y > length; y--) {
			if (a[y]==0) {
				index2 = y;
			} else {
				length = y;
			}
		}

		double[] e = ArrayTools.copy(a,index1,index2);

		return e;
    }

    public static double[] fadeIn(double[] a, int secs) {
    	int time = (int) (StdAudio.SAMPLE_RATE * secs);
    	double[] b = ArrayTools.duplicate(a);

    	for (int i =0; i<time; i++) {
    		b[i]= a[i]*i/time;
    	}

    	return b;

    }

    public static double[] fadeOut(double[] a, int secs) {
 		int time = (int) (StdAudio.SAMPLE_RATE * secs);
    	double[] b = ArrayTools.duplicate(a);


    	for (int i =0; i<time; i++) {
    		b[a.length-i-1]= a[a.length-i-1]*i/time;
    	}
    	
    	return b;
    }

    public static double[] echo(double[] a, int volume, double duration, int repetition){
    	int time = (int) (StdAudio.SAMPLE_RATE * duration);
    	double[] b = new double[repetition*a.length+repetition*time];
    	
    	int volumeInterval = volume/repetition;
    	for(int x=0; x<=repetition-1; x++){
    		double[] c = changeVolume(a, volume-volumeInterval*x);
    		for (int j = 0; j<c.length; j++) {
    			b[j+x*(c.length+time)] = c[j];
    		}
    		for(int y=c.length; y<c.length+time; y++){
    			b[x*(c.length+time)+y] = 0;
    		}
    	}	
    	return b;
    }

    public static double[] sinstuff(double duration, double hz) {
        int n = (int) (StdAudio.SAMPLE_RATE * duration);
        double[] a = new double[n+1];
        for (int i = 0; i <= n; i++) {
            a[i] = Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
        }

        return a;
    }
}
