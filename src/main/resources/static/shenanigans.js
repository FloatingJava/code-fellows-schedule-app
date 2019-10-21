'use strict';

$(() => {
  setEventListeners();
});

function setEventListeners() {
  $('#search-form').on('submit', fetchCourseData);
};

$('#course101').on('change', function(){
  fetchCalendarData('101');
});
$('#course102').on('change', function(){
  fetchCalendarData('102');
});
$('#course201').on('change', function(){
  fetchCalendarData('201');
});
$('#course301').on('change', function(){
  fetchCalendarData('301');
});
$('#course401').on('change', function(){
  fetchCalendarData('401');
});

function fetchCalendarData(course){
  var course101 = $('#course101').val();
  var course102 = $('#course102').val();
  var course201 = $('#course201').val();
  var course301 = $('#course301').val();
  var course401 = $('#course401').val();
  if (course === '101'){ console.log(`Course 101's value is ${course101}`); }
  if (course === '102'){ console.log(`Course 102's value is ${course102}`); }
  if (course === '201'){ console.log(`Course 201's value is ${course201}`); }
  if (course === '301'){ console.log(`Course 301's value is ${course301}`); }
  if (course === '401'){ console.log(`Course 401's value is ${course401}`); }
  // event.preventDefault();
  // console.log(this.val());
  // console.log(course + ' was clicked on');
}

function fetchCourseData(event) {
  event.preventDefault();
  console.log('button clicked');
  let course = $('#StartingPoint').val();
  let EndPoint = $('#EndPoint').val();

  $.ajax({
    url: `/generateEdPlanString/${course}/${EndPoint}`,
    method: 'GET',
    data: { data: course, },
  }).then(returnString => {
    $('#planOutPut').text(returnString);
  })

  $.ajax({
    url: `/generateEdPlan/${course}/${EndPoint}`,
    method: 'GET',
    data: { data: course, },
  }).then(course => {
    let dropDown101 = $('#course101');
    dropDown101.empty();
    let dropDown102 = $('#course102');
    dropDown102.empty();
    let dropDown201 = $('#course201');
    dropDown201.empty();
    let dropDown301 = $('#course301');
    dropDown301.empty();
    let dropDown401 = $('#course401');
    dropDown401.empty();
    console.log(course);
    course.forEach(element => {
      if (element.family.includes('101')) {
        dropDown101.append($('<option></option>')
          .attr('value', element.id)
          .text(element.startDate + ' - ' + element.code));
      }
      if (element.family.includes('102')) {
        dropDown102.append($('<option></option>')
          .attr('value', element.id)
          .text(element.startDate + ' - ' + element.code));
      }
      if (element.family.includes('201')) {
        dropDown201.append($('<option></option>')
          .attr('value', element.id)
          .text(element.startDate + ' - ' + element.code));
      }
      if (element.family.includes('301')) {
        dropDown301.append($('<option></option>')
          .attr('value', element.id)
          .text(element.startDate + ' - ' + element.code));
      }
      if (element.family.includes('401')) {
        dropDown401.append($('<option></option>')
          .attr('value', element.id)
          .text(element.startDate + ' - ' + element.code));
      }
    });
  });
};


