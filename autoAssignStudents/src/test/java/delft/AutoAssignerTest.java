package delft;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import java.time.*;

class AutoAssignerTest {
    private AutoAssigner autoAssigner;
    private List<Student> students;
    private Map<ZonedDateTime, Integer> spots01;
    private Map<ZonedDateTime, Integer> spots02;
    private List<Workshop> workshops;

    private final Student hugo = new Student(1,"Hugo","hugo@");
    private final Student simon = new Student(2,"Simon","simon@");

    private final ZonedDateTime date01 = date(2025,11,4,12,0);
    private final ZonedDateTime date02 = date(2025,11,5,12,0);

    @BeforeEach
    public void init() {
        autoAssigner = new AutoAssigner();
        students = new ArrayList<>();
        spots01 = new HashMap<>();
        spots02 = new HashMap<>();
        workshops = new ArrayList<>();
    }

    @Test
    public void testStudentInfo() {
        students.add(hugo);
        spots01.put(date01, 1);
        workshops.add(new Workshop(1,"VV", spots01));

        AssignmentsLogger assignment = autoAssigner.assign(students, workshops);

        assertEquals(1, students.get(0).getId());
        assertEquals(169746454, students.get(0).hashCode());
        assertEquals("Hugo", students.get(0).getName());
        assertEquals("hugo@", students.get(0).getEmail());
    }

    @Test
    public void testWorkshopInfo() {
        students.add(hugo);
        spots01.put(date01, 1);
        workshops.add(new Workshop(1,"VV", spots01));

        AssignmentsLogger assignment = autoAssigner.assign(students, workshops);

        Workshop workshop = workshops.get(0);
        assertEquals(1, workshop.getId());
        assertEquals(169746454, students.get(0).hashCode());
        assertEquals("VV", workshop.getName());
    }

    @Test
    public void testHappyPath() {
        students.add(hugo);
        spots01.put(date01, 1);
        workshops.add(new Workshop(1,"VV", spots01));

        AssignmentsLogger assignment = autoAssigner.assign(students, workshops);

        assertEquals(1, assignment.getAssignments().size());
        assertEquals("VV,Hugo,04/11/2025 12:00", assignment.getAssignments().get(0));
    }

    @Test
    public void testNoPlaceAvailable() {
        List<Student> students = List.of(hugo, simon);
        spots01.put(date01, 1);
        workshops.add(new Workshop(1,"VV", spots01));

        AssignmentsLogger assignment = autoAssigner.assign(students, workshops);

        assertEquals(1, assignment.getAssignments().size());
        assertEquals("VV,Hugo,04/11/2025 12:00", assignment.getAssignments().get(0));
        assertEquals(1, assignment.getErrors().size());
        assertEquals("VV,Simon", assignment.getErrors().get(0));
    }

    @Test
    public void testNoSpotInDate() {
        List<Student> students = List.of(hugo);
        spots01.put(date01, 0);
        workshops.add(new Workshop(1,"VV", spots01));

        AssignmentsLogger assignment = autoAssigner.assign(students, workshops);

        assertEquals(1, assignment.getErrors().size());
        assertEquals("VV,Hugo", assignment.getErrors().get(0));
    }

    @Test
    public void testTwoDates() {
        List<Student> students = List.of(hugo, simon);
        spots01.put(date01, 1);
        spots01.put(date02, 1);
        workshops.add(new Workshop(1,"VV", spots01));

        AssignmentsLogger assignment = autoAssigner.assign(students, workshops);

        assertEquals(2, assignment.getAssignments().size());
        assertThat(assignment.getAssignments()).containsExactlyInAnyOrder("VV,Hugo,04/11/2025 12:00", "VV,Simon,05/11/2025 12:00");
    }

    @Test
    public void testTwoWorkshops() {
        List<Student> students = List.of(hugo, simon);
        spots01.put(date01, 1);
        spots02.put(date02, 1);

        workshops.add(new Workshop(1,"VV", spots02));
        workshops.add(new Workshop(2, "ASE", spots02));

        AssignmentsLogger assignment = autoAssigner.assign(students, workshops);

        assertEquals(2, assignment.getAssignments().size());
        assertThat(assignment.getAssignments()).containsExactlyInAnyOrder("VV,Hugo,05/11/2025 12:00", "ASE,Hugo,05/11/2025 12:00");
    }

    private ZonedDateTime date(int year, int month, int day, int hour, int minute) {
        return ZonedDateTime.of(year, month, day, hour, minute, 0, 0, ZoneId.systemDefault());
    }
}
