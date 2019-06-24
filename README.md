# NameThatTune

A Java program that randomly generates a 2 to 4 minute song by choosing a scale, generating a chorus and verse, and choosing a song structure.

## Strategy to Generate Music:
	1. Choose a major scale 
	2. Randomly shuffle scale and choose three random pitches to form three major chords
		a. one chorus repeats these three chords once more, making the chorus 6 chords long
		b. the duration of each chord is randomnly chosen between 1 and 4
	3. Number two is basically repeated for the verse except all 5 pitches from the scale are used to form either major or minor chords
		a. the verse is overlayed with single notes randomnly generated by shuffling the scale and repeating it.
			i. the number of times this 10 note pattern is repeated depends on the length of the verse.
			ii. the duration of each note if 1/2 a second 
	4. With the chorus and verse made, a random structure is generated and the chorus and verse pattern is concatenated to form the song.
	5. Fade in and fade out are implemented on to the song for 3 seconds each.
	
## Strategy to Generate Visualization:
	1. The pitches and durations of the chorus and the note array is recorded in their own arrays
	2. When the structure is determined, these arrays are concatenated to form an array of all the pitches and all the durations 
	3. To draw these pitches, two circles are drawn 
		a. the radius of the circle is determined by the pitch, which is scaled to fit between .1 and .2
			i. the second circle is just a border around the first circle
		b. the color is also determined by the pitch, which is scaled between 1 and 10 to fit the range of predetermined colors
		c. the circle stays on the screen for the duration that corresponds to the pitch being visualized and then an animation makes the circle bigger or smaller to move onto the next pitch.
 	4. The only time this pattern is deviated from is when a rest is approaching. Then the radius goes to 0.00001 and the circle becomes black. 
