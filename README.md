# Support Wheel of Fate

<h3> Background</h3>
All engineers in Company X take it in turns to support the business for half a day at a time. Currently,
generating a schedule that shows who’s turn is it to support the business is being done manually
and we need to automate that!
<h3> Task</h3>
Your task is to design and build an online “Support Wheel of Fate”. This should repeat selecting two
engineers at random to both complete a half day of support (shift) each to ultimately generate a
schedule that shows whose turn is it to support the business.

<h3>Assumptions</h3>
You can assume that Company X has 10 engineers.
You can assume that the schedule will span two weeks and start on the first working day of the
upcoming week.

<h3>Rules</h3> 
An engineer can do at most one half day shift in a day.
An engineer cannot have half day shifts on consecutive days.
Each engineer should have completed one whole day of support in any 2 week period.

<h3>Important note</h3>
These rules are liable to change in the future, so make sure your design is flexible enough to be able
to add new rules.

<h3>Deliverables</h3>
At the end of the assignment, the following must be included in the repository:
<ul> 
<li>A consumable API that returns the schedule.</li>
<li> A simple Presentation Layer (Front-end) that shows the schedule. UI is not an important part
of the assessment, any presentation layer that shows the date of the shifts and which
engineer is doing which shift is OK.</li>
</ul>
