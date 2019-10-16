'use strict';

$(() => {
  setEventListeners();
});

function setEventListeners() {
  $('#search-form').on('submit', fetchCourseData);
};

function fetchCourseData(event) {
  event.preventDefault();
  console.log('button clicked');
  let course = $('#StartingPoint').val();
  console.log(course);

  $.ajax({
    url: `/generateEdPlan/${course}`,
    method: 'GET',
    data: { data: course, },
  }).then(course =>
    console.log(course)
  );
};
