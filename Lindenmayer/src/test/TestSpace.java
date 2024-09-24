package test;

import turtle.space.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestSpace{

  public TestSpace(){}

  @Test
  private void test_DegresToRadian(){
    assertEquals(Space.degresToRadian(30),(Math.PI/6),0);
    assertEquals(Space.degresToRadian(45),(Math.PI/4),0);
    assertEquals(Space.degresToRadian(60),(Math.PI/3),0);
    assertEquals(Space.degresToRadian(90),(Math.PI/2),0);
    assertEquals(Space.degresToRadian(180),(Math.PI),0);
    assertEquals(Space.degresToRadian(360),(2*Math.PI),0);
  }

  @Test
  private void test_rotationMatrixU(){
    double[][] mat0 = {{1,0,0},{0,1,0},{0,0,1}};
    double[][] mat30 = {{Math.sqrt(3)/2,0.5,0},{-0.5,Math.sqrt(3)/2,0},{0,0,1}};
    double[][] mat45 = {{Math.sqrt(2)/2,Math.sqrt(2)/2,0},{-(Math.sqrt(2)/2),Math.sqrt(2)/2,0},{0,0,1}};
    double[][] mat60 = {{0.5,Math.sqrt(3)/2,0},{-(Math.sqrt(3)/2),0.5,0},{0,0,1}};
    double[][] mat90 = {{0,1,0},{-1,0,0},{0,0,1}};
    double[][] matricef0 = Space.getRotationMatrixU(0);
    double[][] matricef30 = Space.getRotationMatrixU(30);
    double[][] matricef45 = Space.getRotationMatrixU(45);
    double[][] matricef60 = Space.getRotationMatrixU(60);
    double[][] matricef90 = Space.getRotationMatrixU(90);
    for(int i=0; i<3; i++){
      for(int j=0; j<3; j++){
        assertEquals(matricef0[i][j], mat0[i][j], 1*Math.pow(10,-5));
        assertEquals(matricef30[i][j], mat30[i][j], 1*Math.pow(10,-5));
        assertEquals(matricef45[i][j], mat45[i][j], 1*Math.pow(10,-5));
        assertEquals(matricef60[i][j], mat60[i][j], 1*Math.pow(10,-5));
        assertEquals(matricef90[i][j], mat90[i][j], 1*Math.pow(10,-5));
      }
    }
  }


  @Test
  private void test_rotationMatrixL(){
    double[][] mat0 = {{1,0,0},{0,1,0},{0,0,1}};
    double[][] mat30 = {{Math.sqrt(3)/2,0,-0.5},{0,1,0},{0.5,0,Math.sqrt(3)/2}};
    double[][] mat45 = {{Math.sqrt(2)/2,0,-(Math.sqrt(2)/2)},{0,1,0},{Math.sqrt(2)/2,0,Math.sqrt(2)/2}};
    double[][] mat60 = {{0.5,0,-(Math.sqrt(3)/2)},{0,1,0},{Math.sqrt(3)/2,0,0.5}};
    double[][] mat90 = {{0,0,-1},{0,1,0},{1,0,0}};
    double[][] matricef0 = Space.getRotationMatrixL(0);
    double[][] matricef30 = Space.getRotationMatrixL(30);
    double[][] matricef45 = Space.getRotationMatrixL(45);
    double[][] matricef60 = Space.getRotationMatrixL(60);
    double[][] matricef90 = Space.getRotationMatrixL(90);
    for(int i=0; i<3; i++){
      for(int j=0; j<3; j++){
        assertEquals(matricef0[i][j], mat0[i][j], 1*Math.pow(10,-5));
        assertEquals(matricef30[i][j], mat30[i][j], 1*Math.pow(10,-5));
        assertEquals(matricef45[i][j], mat45[i][j], 1*Math.pow(10,-5));
        assertEquals(matricef60[i][j], mat60[i][j], 1*Math.pow(10,-5));
        assertEquals(matricef90[i][j], mat90[i][j], 1*Math.pow(10,-5));
      }
    }
  }


  @Test
  private void test_rotationMatrixH(){
    double[][] mat0 = {{1,0,0},{0,1,0},{0,0,1}};
    double[][] mat30 = {{1,0,0},{0,Math.sqrt(3)/2,-0.5},{0,0.5,Math.sqrt(3)/2}};
    double[][] mat45 = {{1,0,0},{0,Math.sqrt(2)/2,-(Math.sqrt(2)/2)},{0,Math.sqrt(2)/2,Math.sqrt(2)/2}};
    double[][] mat60 = {{1,0,0},{0,0.5,-(Math.sqrt(3)/2)},{0,Math.sqrt(3)/2,0.5}};
    double[][] mat90 = {{1,0,0},{0,0,-1},{0,1,0}};
    double[][] matricef0 = Space.getRotationMatrixH(0);
    double[][] matricef30 = Space.getRotationMatrixH(30);
    double[][] matricef45 = Space.getRotationMatrixH(45);
    double[][] matricef60 = Space.getRotationMatrixH(60);
    double[][] matricef90 = Space.getRotationMatrixH(90);
    for(int i=0; i<3; i++){
      for(int j=0; j<3; j++){
        assertEquals(matricef0[i][j], mat0[i][j], 1*Math.pow(10,-5));
        assertEquals(matricef30[i][j], mat30[i][j], 1*Math.pow(10,-5));
        assertEquals(matricef45[i][j], mat45[i][j], 1*Math.pow(10,-5));
        assertEquals(matricef60[i][j], mat60[i][j], 1*Math.pow(10,-5));
        assertEquals(matricef90[i][j], mat90[i][j], 1*Math.pow(10,-5));
      }
    }
  }


  @Test
  private void test_Rota3D(){
    double[] point1 = {1,2,3};
    double[] point2 = {4,5,6};
    double[][] matrice1 = {{1,2,3},{2,1,2},{3,2,1}};
    double[][] matrice2 = {{1,0,0},{0,1,0},{0,0,1}};
    double[][] matrice3 = {{1,0,0},{2,1,0},{3,2,1}};
    double[][] matrice4 = {{3,2,1},{5,4,3},{4,4,3}};


    //pour point x matrices
    double[] rep3D1 = Space.Rota3D(point1,matrice1);
    double[] rep3D2 = Space.Rota3D(point1,matrice2);
    double[] rep3D3 = Space.Rota3D(point2,matrice3);
    double[] rep1 = {14,10,10};
    double[] rep2 = {1,2,3};
    double[] rep3 = {32,17,6};
    // ^^ Pour tester

    //pour matrice x matrice
    double[][] res3D1 = Space.Rota3D(matrice1,matrice3);
    double[][] res3D2 = Space.Rota3D(matrice4,matrice3);
    double[][] res3D3 = Space.Rota3D(matrice4,matrice1);
    double[][] res1 = {{14,8,3},{10,5,2},{10,4,1}};
    double[][] res2 = {{10,4,1},{22,10,3},{21,10,3}};
    double[][] res3 = {{10,10,14},{22,20,26},{21,18,23}};




    //test les produits matriciels de 2 matrices, une 1x3 et l'autre 3x3
    for(int i=0; i<3; i++){
      assertEquals(rep1[i],rep3D1[i],0);
      assertEquals(rep2[i],rep3D2[i],0);
      assertEquals(rep3[i],rep3D3[i],0);
    }


    //test les produits matriciels de 2 matrices, une 3x3 et l'autre 3x3
    for(int i=0; i<3; i++){
      for(int j=0; j<3; j++){
        assertEquals(res1[i][j],res3D1[i][j],0);
        assertEquals(res2[i][j],res3D2[i][j],0);
        assertEquals(res3[i][j],res3D3[i][j],0);
      }
    }
  }









  @Test
  public void toutTestSpace(){
    test_DegresToRadian();
    test_rotationMatrixU();
    test_rotationMatrixL();
    test_rotationMatrixH();
    test_Rota3D();
  }

}
