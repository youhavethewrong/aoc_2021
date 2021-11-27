use chrono::{DateTime, Local};

pub fn time_until_start() -> String {
    let start = DateTime::parse_from_str("2021-12-01 00:00:00 -04:00", "%Y-%m-%d %H:%M:%S %z")
        .unwrap()
        .with_timezone(&Local);
    let now = Local::now();
    format!("{}", start - now)
}
