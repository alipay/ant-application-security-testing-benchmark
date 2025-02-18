// package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.expression.new_expression;

// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import java.util.HashMap;
// import java.util.Map;

// /**
//  * Introduction 完整度->单应用跟踪完整度->表达式->new表达式->new操作+数组对象
//  * Level 2
//  * Date 2024-05-09
//  */
// // evaluation information start
// // real case = true
// // evaluation item = 完整度->单应用跟踪完整度->表达式->new表达式
// // bind_url = completeness/single_app_tracing/expression/new_expression/Expression_NewExpression_Array_001_T
// // evaluation information end
// @RestController()
// @RequestMapping("completeness/single_app_tracing/expression/new_expression")
// public class Expression_NewExpression_Array_001_T {
//     @PostMapping(value = "Expression_NewExpression_Array_001_T")
//     public Map<String, Object> aTaintCase0195(@RequestParam String[] cmd) {
//         Map<String, Object> modelMap = new HashMap<>();
//         try {
//             String[] chars = new String[]{cmd[0],cmd[1]};
//             Runtime.getRuntime().exec(chars);
//             modelMap.put("status", "success");
//         } catch (Exception e) {
//             modelMap.put("status", "error");
//         }
//         return modelMap;
//     }
// }
