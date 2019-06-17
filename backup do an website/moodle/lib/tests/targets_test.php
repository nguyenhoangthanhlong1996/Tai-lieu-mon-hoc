<?php
// This file is part of Moodle - http://moodle.org/
//
// Moodle is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// Moodle is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with Moodle.  If not, see <http://www.gnu.org/licenses/>.

/**
 * Unit tests for core targets.
 *
 * @package   core
 * @category  analytics
 * @copyright 2019 Victor Deniz <victor@moodle.com>
 * @license   http://www.gnu.org/copyleft/gpl.html GNU GPL v3 or later
 */

defined('MOODLE_INTERNAL') || die();
global $CFG;

require_once($CFG->dirroot . '/completion/criteria/completion_criteria.php');
require_once($CFG->dirroot . '/completion/criteria/completion_criteria_activity.php');
require_once($CFG->dirroot . '/lib/grade/grade_item.php');
require_once($CFG->dirroot . '/lib/grade/grade_grade.php');
require_once($CFG->dirroot . '/lib/grade/grade_category.php');
require_once($CFG->dirroot . '/lib/grade/constants.php');

/**
 * Unit tests for core targets.
 *
 * @package   core
 * @category  analytics
 * @copyright 2019 Victor Deniz <victor@moodle.com>
 * @license   http://www.gnu.org/copyleft/gpl.html GNU GPL v3 or later
 */
class core_analytics_targets_testcase extends advanced_testcase {

    /**
     * Provides course params for the {@link self::test_core_target_course_completion_analysable()} method.
     *
     * @return array
     */
    public function analysable_provider() {

        $now = new DateTime("now", core_date::get_server_timezone_object());
        $year = $now->format('Y');
        $month = $now->format('m');

        return [
            'coursenotyetstarted' => [
                'params' => [
                    'enablecompletion' => 1,
                    'startdate' => mktime(0, 0, 0, 10, 24, $year + 1)
                ],
                'isvalid' => get_string('coursenotyetstarted', 'course')
            ],
            'coursenostudents' => [
                'params' => [
                    'enablecompletion' => 1,
                    'startdate' => mktime(0, 0, 0, 10, 24, $year - 2),
                    'enddate' => mktime(0, 0, 0, 10, 24, $year - 1)
                ],
                'isvalid' => get_string('nocoursestudents', 'course')
            ],
            'coursenosections' => [
                'params' => [
                    'enablecompletion' => 1,
                    'format' => 'social',
                    'students' => true
                ],
                'isvalid' => get_string('nocoursesections', 'course')
            ],
            'coursenoendtime' => [
                'params' => [
                    'enablecompletion' => 1,
                    'format' => 'topics',
                    'enddate' => 0,
                    'students' => true
                ],
                'isvalid' => get_string('nocourseendtime', 'course')
            ],
            'courseendbeforestart' => [
                'params' => [
                    'enablecompletion' => 1,
                    'enddate' => mktime(0, 0, 0, 10, 23, $year - 2),
                    'students' => true
                ],
                'isvalid' => get_string('errorendbeforestart', 'course')
            ],
            'coursetoolong' => [
                'params' => [
                    'enablecompletion' => 1,
                    'startdate' => mktime(0, 0, 0, 10, 24, $year - 2),
                    'enddate' => mktime(0, 0, 0, 10, 23, $year),
                    'students' => true
                ],
                'isvalid' => get_string('coursetoolong', 'course')
            ],
            'coursealreadyfinished' => [
                'params' => [
                    'enablecompletion' => 1,
                    'startdate' => mktime(0, 0, 0, 10, 24, $year - 2),
                    'enddate' => mktime(0, 0, 0, 10, 23, $year - 1),
                    'students' => true
                ],
                'isvalid' => get_string('coursealreadyfinished', 'course'),
                'fortraining' => false
            ],
            'coursenotyetfinished' => [
                'params' => [
                    'enablecompletion' => 1,
                    'startdate' => mktime(0, 0, 0, $month - 1, 24, $year),
                    'enddate' => mktime(0, 0, 0, $month + 2, 23, $year),
                    'students' => true
                ],
                'isvalid' => get_string('coursenotyetfinished', 'course')
            ],
            'coursenocompletion' => [
                'params' => [
                    'enablecompletion' => 0,
                    'startdate' => mktime(0, 0, 0, $month - 2, 24, $year),
                    'enddate' => mktime(0, 0, 0, $month - 1, 23, $year),
                    'students' => true
                ],
                'isvalid' => get_string('completionnotenabledforcourse', 'completion')
            ],
        ];
    }

    /**
     * Provides enrolment params for the {@link self::test_core_target_course_completion_samples()} method.
     *
     * @return array
     */
    public function sample_provider() {
        $now = time();
        return [
            'enrolmentendbeforecourse' => [
                'coursestart' => $now,
                'courseend' => $now + (WEEKSECS * 8),
                'timestart' => $now,
                'timeend' => $now - DAYSECS,
                'isvalidfortraining' => false,
                'isvalidforprediction' => false
            ],
            'enrolmenttoolong' => [
                'coursestart' => $now,
                'courseend' => $now + (WEEKSECS * 8),
                'timestart' => $now - (YEARSECS + (WEEKSECS * 8)),
                'timeend' => $now + (WEEKSECS * 8),
                'isvalidfortraining' => false,
                'isvalidforprediction' => false
            ],
            'enrolmentstartaftercourse' => [
                'coursestart' => $now,
                'courseend' => $now + (WEEKSECS * 8),
                'timestart' => $now + (WEEKSECS * 9),
                'timeend' => $now + (WEEKSECS * 10),
                'isvalidfortraining' => false,
                'isvalidforprediction' => false
            ],
            'enrolmentstartsafternow' => [
                'coursestart' => $now,
                'courseend' => $now + (WEEKSECS * 8),
                'timestart' => $now + (WEEKSECS * 2),
                'timeend' => $now + (WEEKSECS * 7),
                'isvalidfortraining' => false,
                'isvalidforprediction' => false
            ],
            'enrolmentfinishedbeforenow' => [
                'coursestart' => $now - (WEEKSECS * 4),
                'courseend' => $now - (WEEKSECS * 1),
                'timestart' => $now - (WEEKSECS * 3),
                'timeend' => $now - (WEEKSECS * 2),
                'isvalidfortraining' => true,
                'isvalidforprediction' => false
            ],
        ];
    }

    /**
     * Test the conditions of a valid analysable, both common and specific to this target (course_completion).
     *
     * @dataProvider analysable_provider
     * @param mixed $courseparams Course data
     * @param true|string $isvalid True when analysable is valid, string when it is not
     * @param boolean $fortraining True if the course is for training the model
     */
    public function test_core_target_course_completion_analysable($courseparams, $isvalid, $fortraining = true) {
        global $DB;

        $this->resetAfterTest(true);

        try {
            $course = $this->getDataGenerator()->create_course($courseparams);
        } catch (moodle_exception $e) {
            $course = $this->getDataGenerator()->create_course();
            $courserecord = $courseparams;
            $courserecord['id'] = $course->id;
            unset($courserecord['students']);

            $DB->update_record_raw('course', $courserecord);
            $course = get_course($course->id);
        }
        $user = $this->getDataGenerator()->create_user();

        if (!empty($courseparams['enablecompletion'])) {
            $assign = $this->getDataGenerator()->create_module('assign', ['course' => $course->id, 'completion' => 1]);
            $cm = get_coursemodule_from_id('assign', $assign->cmid);

            $criteriadata = (object) [
                'id' => $course->id,
                'criteria_activity' => [
                    $cm->id => 1
                ]
            ];
            $criterion = new completion_criteria_activity();
            $criterion->update_config($criteriadata);
        }

        $target = new \core_course\analytics\target\course_completion();

        // Test valid analysables.

        if (!empty($courseparams['students'])) {
            // Enroll user in course.
            $this->getDataGenerator()->enrol_user($user->id, $course->id);
        }

        $analysable = new \core_analytics\course($course);
        $this->assertEquals($isvalid, $target->is_valid_analysable($analysable, $fortraining));
    }

    /**
     * Test the conditions of a valid sample, both common and specific to this target (course_completion).
     *
     * @dataProvider sample_provider
     * @param int $coursestart Course start date
     * @param int $courseend Course end date
     * @param int $timestart Enrol start date
     * @param int $timeend Enrol end date
     * @param boolean $isvalidfortraining True when sample is valid for training, false when it is not
     * @param boolean $isvalidforprediction True when sample is valid for prediction, false when it is not
     */
    public function test_core_target_course_completion_samples($coursestart, $courseend, $timestart, $timeend,
            $isvalidfortraining, $isvalidforprediction) {

        $this->resetAfterTest(true);

        $courserecord = new stdClass();
        $courserecord->startdate = $coursestart;
        $courserecord->enddate = $courseend;

        $user = $this->getDataGenerator()->create_user();
        $course = $this->getDataGenerator()->create_course($courserecord);
        $this->getDataGenerator()->enrol_user($user->id, $course->id, null, 'manual', $timestart, $timeend);

        $target = new \core_course\analytics\target\course_completion();
        $analyser = new \core\analytics\analyser\student_enrolments(1, $target, [], [], []);
        $analysable = new \core_analytics\course($course);

        $class = new ReflectionClass('\core\analytics\analyser\student_enrolments');
        $method = $class->getMethod('get_all_samples');
        $method->setAccessible(true);

        list($sampleids, $samplesdata) = $method->invoke($analyser, $analysable);
        $target->add_sample_data($samplesdata);
        $sampleid = reset($sampleids);

        $this->assertEquals($isvalidfortraining, $target->is_valid_sample($sampleid, $analysable, true));
        $this->assertEquals($isvalidforprediction, $target->is_valid_sample($sampleid, $analysable, false));
    }

    /**
     * Setup user, framework, competencies and course competencies.
     */
    protected function setup_competencies_environment() {
        $this->resetAfterTest(true);
        $now = time();
        $this->setAdminUser();
        $dg = $this->getDataGenerator();
        $lpg = $dg->get_plugin_generator('core_competency');

        $course = $dg->create_course(array('startdate' => $now - WEEKSECS, 'enddate' => $now - DAYSECS));
        $coursenocompetencies = $dg->create_course(array('startdate' => $now - WEEKSECS, 'enddate' => $now - DAYSECS));

        $u1 = $dg->create_user();
        $this->getDataGenerator()->enrol_user($u1->id, $course->id);
        $this->getDataGenerator()->enrol_user($u1->id, $coursenocompetencies->id);
        $f1 = $lpg->create_framework();
        $c1 = $lpg->create_competency(array('competencyframeworkid' => $f1->get('id')));
        $c2 = $lpg->create_competency(array('competencyframeworkid' => $f1->get('id')));
        $c3 = $lpg->create_competency(array('competencyframeworkid' => $f1->get('id')));
        $c4 = $lpg->create_competency(array('competencyframeworkid' => $f1->get('id')));
        $cc1 = $lpg->create_course_competency(array('competencyid' => $c1->get('id'), 'courseid' => $course->id,
            'ruleoutcome' => \core_competency\course_competency::OUTCOME_NONE));
        $cc2 = $lpg->create_course_competency(array('competencyid' => $c2->get('id'), 'courseid' => $course->id,
            'ruleoutcome' => \core_competency\course_competency::OUTCOME_EVIDENCE));
        $cc3 = $lpg->create_course_competency(array('competencyid' => $c3->get('id'), 'courseid' => $course->id,
            'ruleoutcome' => \core_competency\course_competency::OUTCOME_RECOMMEND));
        $cc4 = $lpg->create_course_competency(array('competencyid' => $c4->get('id'), 'courseid' => $course->id,
            'ruleoutcome' => \core_competency\course_competency::OUTCOME_COMPLETE));

        return array(
            'course' => $course,
            'coursenocompetencies' => $coursenocompetencies,
            'user' => $u1,
            'course_competencies' => array($cc1, $cc2, $cc3, $cc4)
        );
    }

     /**
      * Test the specific conditions of a valid analysable for the course_competencies target.
      */
    public function test_core_target_course_competencies_analysable() {

        $data = $this->setup_competencies_environment();

        $analysable = new \core_analytics\course($data['course']);
        $target = new \core_course\analytics\target\course_competencies();

        $this->assertTrue($target->is_valid_analysable($analysable));

        $analysable = new \core_analytics\course($data['coursenocompetencies']);
        $this->assertEquals(get_string('nocompetenciesincourse', 'tool_lp'), $target->is_valid_analysable($analysable));
    }

    /**
     * Test the target value calculation.
     */
    public function test_core_target_course_competencies_calculate() {

        $data = $this->setup_competencies_environment();

        $target = new \core_course\analytics\target\course_competencies();
        $analyser = new \core\analytics\analyser\student_enrolments(1, $target, [], [], []);
        $analysable = new \core_analytics\course($data['course']);

        $class = new ReflectionClass('\core\analytics\analyser\student_enrolments');
        $method = $class->getMethod('get_all_samples');
        $method->setAccessible(true);

        list($sampleids, $samplesdata) = $method->invoke($analyser, $analysable);
        $target->add_sample_data($samplesdata);
        $sampleid = reset($sampleids);

        $class = new ReflectionClass('\core_course\analytics\target\course_competencies');
        $method = $class->getMethod('calculate_sample');
        $method->setAccessible(true);

        // Method calculate_sample() returns 1 when the user has not achieved all the competencies assigned to the course.
        $this->assertEquals(1, $method->invoke($target, $sampleid, $analysable));

        // Grading of all the competences assigned to the course, in such way that the user achieves them all.
        foreach ($data['course_competencies'] as $competency) {
            \core_competency\api::grade_competency_in_course($data['course']->id, $data['user']->id,
                    $competency->get('competencyid'), 3, 'Unit test');
        }
        // Method calculate_sample() returns 0 when the user has achieved all the competencies assigned to the course.
        $this->assertEquals(0, $method->invoke($target, $sampleid, $analysable));
    }

    /**
     * Test the specific conditions of a valid analysable for the course_gradetopass target.
     */
    public function test_core_target_course_gradetopass_analysable() {
        global $DB;

        $this->resetAfterTest(true);
        $now = time();

        $dg = $this->getDataGenerator();

        // Course without grade to pass set.
        $course1 = $dg->create_course(array('startdate' => $now - WEEKSECS, 'enddate' => $now - DAYSECS));
        $student1 = $dg->create_user();
        $studentrole = $DB->get_record('role', array('shortname' => 'student'));
        $dg->enrol_user($student1->id, $course1->id, $studentrole->id);

        $analysable = new \core_analytics\course($course1);
        $target = new \core_course\analytics\target\course_gradetopass();
        $this->assertEquals(get_string('gradetopassnotset', 'course'), $target->is_valid_analysable($analysable));

        // Set grade to pass.
        $courseitem = grade_item::fetch_course_item($course1->id);
        $courseitem->gradepass = 50;
        $DB->update_record('grade_items', $courseitem);
        // Since the grade to pass value is cached in the target, a new one it is instanciated.
        $target = new \core_course\analytics\target\course_gradetopass();
        $this->assertTrue($target->is_valid_analysable($analysable));

    }

    /**
     * Test the target value calculation of the course_gradetopass target.
     */
    public function test_core_target_course_gradetopass_calculate() {
        global $DB;

        $this->resetAfterTest(true);

        $dg = $this->getDataGenerator();
        $course1 = $dg->create_course();
        // Set grade to pass.
        $student1 = $dg->create_user();
        $student2 = $dg->create_user();
        $student3 = $dg->create_user();
        $student4 = $dg->create_user();
        $studentrole = $DB->get_record('role', array('shortname' => 'student'));
        $dg->enrol_user($student1->id, $course1->id, $studentrole->id);
        $dg->enrol_user($student2->id, $course1->id, $studentrole->id);
        $dg->enrol_user($student3->id, $course1->id, $studentrole->id);

        $enrolstart = mktime(0, 0, 0, 10, 25, 2015);
        $dg->enrol_user($student4->id, $course1->id, $studentrole->id, 'manual', $enrolstart);

        // get_all_samples() does not guarantee any order, so let's
        // explicitly define the expectations here for later comparing.
        // Expectations format being array($userid => expectation, ...)
        $expectations = [];

        $courseitem = grade_item::fetch_course_item($course1->id);
        // Student1 (< gradepass) fails, so it's non achieved sample.
        $courseitem->update_final_grade($student1->id, 30);
        $expectations[$student1->id] = 1;

        // Student2 (> gradepass) passes, so it's achieved sample.
        $courseitem->update_final_grade($student2->id, 60);
        $expectations[$student2->id] = 0;

        // Student 3 (has no grade) fails, so it's non achieved sample.
        $expectations[$student3->id] = 1;

        // Student 4 should be null as its enrolment timestart is after the this range.
        $expectations[$student4->id] = null;

        $courseitem->gradepass = 50;
        $DB->update_record('grade_items', $courseitem);

        $target = new \core_course\analytics\target\course_gradetopass();
        $analyser = new \core\analytics\analyser\student_enrolments(1, $target, [], [], []);
        $analysable = new \core_analytics\course($course1);

        $class = new ReflectionClass('\core\analytics\analyser\student_enrolments');
        $method = $class->getMethod('get_all_samples');
        $method->setAccessible(true);

        list($sampleids, $samplesdata) = $method->invoke($analyser, $analysable);
        $target->add_sample_data($samplesdata);

        $class = new ReflectionClass('\core_course\analytics\target\course_gradetopass');
        $method = $class->getMethod('calculate_sample');
        $method->setAccessible(true);

        $starttime = mktime(0, 0, 0, 10, 24, 2015);

        // Verify all the expectations are fulfilled.
        foreach ($sampleids as $sampleid => $key) {
            $this->assertEquals($expectations[$samplesdata[$key]['user']->id], $method->invoke($target, $sampleid,
                $analysable, $starttime));
        }
    }
}
