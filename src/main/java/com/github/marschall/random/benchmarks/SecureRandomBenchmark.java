package com.github.marschall.random.benchmarks;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import com.github.marschall.getentropy.GetentropyProvider;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class SecureRandomBenchmark {

  @Param({
//    GetrandomProvider.GETURANDOM,
    "NativePRNGNonBlocking", // /dev/urandom
//    RdrandProvider.ALGORITHM,
    GetentropyProvider.NAME,
    "SHA1PRNG",
    "LCG"}) // java.util.Random
  public String algorithm;

  private Random random;

  private byte[] bytes16;
  private byte[] bytes32;
  private byte[] bytes64;
  private byte[] bytes128;
  private byte[] bytes256;

  @Setup
  public void setup() throws NoSuchAlgorithmException {
    if (this.algorithm.equals("LCG")) {
      this.random = new Random();
    } else {
      this.random = SecureRandom.getInstance(this.algorithm);
    }
    this.random.setSeed(23L);
    this.random.nextBoolean(); // seed
    this.bytes16 = new byte[16];
    this.bytes32 = new byte[32];
    this.bytes64 = new byte[64];
    this.bytes128 = new byte[128];
    this.bytes256 = new byte[256];
  }

//  @Benchmark
  public boolean nextBoolean() {
    return this.random.nextBoolean();
  }

//  @Benchmark
  public int nextInt() {
    return this.random.nextInt();
  }

//  @Benchmark
  public long nextLong() {
    return this.random.nextLong();
  }

  @Benchmark
  public byte[] nextBytes16() {
    this.random.nextBytes(this.bytes16);
    return this.bytes16;
  }

//  @Benchmark
  public byte[] nextBytes32() {
    this.random.nextBytes(this.bytes32);
    return this.bytes32;
  }

//  @Benchmark
  public byte[] nextBytes64() {
    this.random.nextBytes(this.bytes64);
    return this.bytes64;
  }

//  @Benchmark
  public byte[] nextBytes128() {
    this.random.nextBytes(this.bytes128);
    return this.bytes128;
  }

//  @Benchmark
  public byte[] nextBytes256() {
    this.random.nextBytes(this.bytes256);
    return this.bytes256;
  }

}
