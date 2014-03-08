package com.munir.jxls.util;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.FreeRefFunction;

public class Weibul implements FreeRefFunction {

	static final void checkValue(double result) throws EvaluationException {
        if (Double.isNaN(result) || Double.isInfinite(result)) {
            throw new EvaluationException(ErrorEval.NUM_ERROR);
        }
    }
	
	@Override
	public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
		
		if (args.length != 3) {  
            return ErrorEval.VALUE_INVALID;
        }

        double i1, i2, i3, result;
        boolean i4;
        try {
            ValueEval v1 = OperandResolver.getSingleValue( args[0], ec.getRowIndex(),ec.getColumnIndex() ) ;
            ValueEval v2 = OperandResolver.getSingleValue( args[1], ec.getRowIndex(),ec.getColumnIndex() ) ;
            ValueEval v3 = OperandResolver.getSingleValue( args[2], ec.getRowIndex(),ec.getColumnIndex() ) ;
            ValueEval v4 = OperandResolver.getSingleValue( args[3], ec.getRowIndex(),ec.getColumnIndex() ) ;

            i1  = OperandResolver.coerceValueToDouble( v1 ) ; 
            i2  = OperandResolver.coerceValueToDouble( v2 ) ;
            i3  = OperandResolver.coerceValueToDouble( v3 ) ;
            i4  = OperandResolver.coerceValueToBoolean( v4, true ) ;
            
            result = getWeibulResult(i1,i2,i3,i4) ;
            
            checkValue(result);
            
        } catch (EvaluationException e) {
            e.printStackTrace() ;
            return e.getErrorEval();
        }

        return new NumberEval( result ) ;
	}


	private double getWeibulResult(double i1,double i2,double i3, boolean i4){
		
		//WeibullDistribution wd = new WeibullDistribution(i2,i3);
		//wd.
		return 0.1;
	}
}
