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

pub fn sliding_window_depth_count(mut depth_readings: Vec<i32>) -> i32 {
    let mut s = 0;
    let mut last: i32 = -1;
    while depth_readings.len() > 2 {
        let a = depth_readings[0];
        let b = depth_readings[1];
        let c = depth_readings[2];
        let current = a + b + c;
        if last > 0 && current > last {
            s += 1;
        }
        depth_readings.remove(0);
        last = current;
    }
    s
}

pub fn backwards_sliding_window_depth_count(mut depth_readings: Vec<i32>) -> i32 {
    let mut s = 0;
    let mut last: i32 = -1;
    while depth_readings.len() > 2 {
        let l = depth_readings.len();
        let a = depth_readings[l - 3];
        let b = depth_readings[l - 2];
        let c = depth_readings[l - 1];
        let current = a + b + c;
        if last > 0 && current < last {
            s += 1;
        }
        depth_readings.pop();
        last = current;
    }
    s
}
