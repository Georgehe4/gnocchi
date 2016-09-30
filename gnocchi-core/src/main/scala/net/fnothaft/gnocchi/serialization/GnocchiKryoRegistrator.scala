/**
 * Copyright 2015 Frank Austin Nothaft
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.fnothaft.gnocchi.serialization

import com.esotericsoftware.kryo.Kryo
import org.apache.spark.serializer.KryoRegistrator
import org.bdgenomics.adam.serialization.AvroSerializer
import org.bdgenomics.formats.avro.{ Genotype, Variant }
import scala.collection.mutable.{ WrappedArray }
import net.fnothaft.gnocchi.models.{ Phenotype, Association, Similarity }

class GnocchiKryoRegistrator extends KryoRegistrator {
  override def registerClasses(kryo: Kryo) {
    kryo.register(classOf[Array[org.apache.spark.sql.types.StructType]])
    kryo.register(classOf[org.apache.spark.sql.types.StructType])

    // scala.collection.mutable
    kryo.register(classOf[scala.collection.mutable.ArrayBuffer[_]])
    kryo.register(classOf[scala.collection.mutable.WrappedArray.ofInt])
    kryo.register(classOf[scala.collection.mutable.WrappedArray.ofLong])
    kryo.register(classOf[scala.collection.mutable.WrappedArray.ofByte])
    kryo.register(classOf[scala.collection.mutable.WrappedArray.ofChar])
    kryo.register(classOf[scala.collection.mutable.WrappedArray.ofRef[_]])
    //kryo.register(classOf[Phenotype.ofRef[Array[Double]]])

    kryo.register(classOf[Similarity])
    kryo.register(classOf[Association])
    kryo.register(classOf[Genotype], new AvroSerializer[Genotype]())
    kryo.register(classOf[Variant], new AvroSerializer[Variant]())
  }
}
