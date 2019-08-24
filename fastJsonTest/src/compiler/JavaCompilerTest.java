package compiler;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class JavaCompilerTest {

	public static void main(String[] args) throws IOException {
		// 获取系统的java编译器
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		// 编译java文件
		int result = compiler.run(null, null, null, "D:/skycloud/fastJsonTest/src/compiler/CTest.java");
		System.out.println(result == 0 ? "success" : "fail");
		// 在程式中运行test
		Runtime run = Runtime.getRuntime();
		Process p = run.exec("java CTest");
		BufferedInputStream in = new BufferedInputStream(p.getInputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String s;
		while ((s = br.readLine()) != null) {
			System.out.println(s);
		}

	}

}