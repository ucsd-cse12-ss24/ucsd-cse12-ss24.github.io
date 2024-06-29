import java.time.LocalDateTime;

public class Event {
  LocalDateTime start;
  LocalDateTime end;
  String location;

  public Event(LocalDateTime start, LocalDateTime end, String location) {
    this.start = start;
    this.end = end;
    this.location = location;
  }

  // Return true if the other event is in 
  // the same room at an overlapping time
  public boolean conflict(Event other) {

    // check if in the same room, otherwise returns false
    if (!this.location.equals(other.location)) { return false; }

    // check if time overlaps
    // LocalDateTime.compareTo(...):
    //  < 0 := before
    //  = 0 := same
    //  > 0 : after
	  
    // this is our code for now.
    // it is maybe buggy, maybe not. 
    // how do we know?
	  if ((this.start.compareTo(other.start) <= 0) &&
	  	  (this.end.compareTo(other.start) > 0))
	  {
		  return true;
	  }

	  
	  return false;
  }
}
