pub fn increasing_depth_count(depth_readings: Vec<i32>) -> i32 {
    let mut s = 0;
    let mut last: i32 = -1;
    for current in depth_readings {
        if last > 0 && current > last {
            s += 1;
        }
        last = current;
    }
    s
}

pub fn sliding_window_depth_count(depth_readings: Vec<i32>) -> i32 {
    0
}
