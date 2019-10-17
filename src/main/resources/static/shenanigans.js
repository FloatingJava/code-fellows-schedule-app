// 'use strict';

// $(() => {
//   setEventListeners();
// });

// function setEventListeners() {
//   $('#search-form').on('submit', fetchCourseData);
// };

// function fetchCourseData(event) {
//   event.preventDefault();
//   console.log('button clicked');
//   let course = $('#StartingPoint').val();
//   console.log(course);

//   $.ajax({
//     url: `/generateEdPlan/${course}`,
//     method: 'GET',
//     data: { data: course, },
//   }).then(course =>
//     console.log(course)
//   );
// };

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
  let EndPoint = $('#EndPoint').val();
  //  console.log(course);

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
        console.log(`${element.code} contains 101`);
      }
      if (element.family.includes('102')) {
        dropDown102.append($('<option></option>')
          .attr('value', element.id)
          .text(element.startDate + ' - ' + element.code));
        console.log(`${element.code} contains 102`);
      }
      if (element.family.includes('201')) {
        dropDown201.append($('<option></option>')
          .attr('value', element.id)
          .text(element.startDate + ' - ' + element.code));
        console.log(`${element.code} contains 201`);
      } 
      if (element.family.includes('301')) {
        dropDown301.append($('<option></option>')
          .attr('value', element.id)
          .text(element.startDate + ' - ' + element.code));
        console.log(`${element.code} contains 301`);
      }
      if (element.family.includes('401')) {
        dropDown401.append($('<option></option>')
          .attr('value', element.id)
          .text(element.startDate + ' - ' + element.code));
        console.log(`${element.code} contains 401`);
      }
    });
    console.log(course);

  });

};


