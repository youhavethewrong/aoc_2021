extern crate aoc_2021;

use aoc_2021::day1::*;
use aoc_2021::day2::*;
use std::fs::File;
use std::io::prelude::*;
use std::io::{BufReader, Error};

#[allow(dead_code)]
fn convert_file_to_string(input: &str) -> Result<String, Error> {
    let file = File::open(input)?;
    let mut buf_reader = BufReader::new(file);
    let mut contents = String::new();
    buf_reader.read_to_string(&mut contents)?;
    Ok(contents)
}

fn convert_file_contents_to_numbers(input: &str) -> Result<Vec<i32>, Error> {
    let contents = convert_file_to_string(input)?;
    let lines = contents.split('\n');
    let mut numbers: Vec<i32> = vec![];
    for line in lines {
        if !line.is_empty() {
            let number: i32 = line.parse().unwrap();
            numbers.push(number);
        }
    }
    Ok(numbers)
}

fn main() -> Result<(), Error> {
    println!("Hello, 2021!");
    let input = std::env::args().nth(1).expect("\nprovide a filename\n");
    let day2 = convert_file_to_string(&input)?;
    let (hp, d, _) = navigate_with_aim(&day2);
    println!("Navigate {}", hp * d);
    Ok(())
}
