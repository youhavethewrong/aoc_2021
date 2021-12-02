pub fn navigate(course: &str) -> (i32, i32) {
    course.lines().fold((0, 0), |(hp, d), line| {
        let (dir, n) = line.split_once(" ").unwrap();
        match (dir, n.parse::<i32>().unwrap()) {
            ("forward", n) => (hp + n, d),
            ("up", n) => (hp, d - n),
            ("down", n) => (hp, d + n),
            _ => unreachable!(),
        }
    })
}

pub fn navigate_with_aim(course: &str) -> (i32, i32, i32) {
    course.lines().fold((0, 0, 0), |(hp, d, aim), line| {
        let (dir, n) = line.split_once(" ").unwrap();
        match (dir, n.parse::<i32>().unwrap()) {
            ("forward", n) => (hp + n, d + (aim * n), aim),
            ("up", n) => (hp, d, aim - n),
            ("down", n) => (hp, d, aim + n),
            _ => unreachable!(),
        }
    })
}
