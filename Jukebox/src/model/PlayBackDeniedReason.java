/*
 * @ - Author: Abhishek Rane
 * @ - Author: Bryce Hammond
 */
package model;

import java.io.Serializable;
/*
 * This enum allows the student to tell any UI why a song wasnt played.
 */
public enum PlayBackDeniedReason implements Serializable{
	MAX_DAILY_PLAYS_REACHED, INSUFFICEIENT_TIME;
}
